package com.fachrizalmrsln.githubuserapp.data.usecase

import com.fachrizalmrsln.githubuserapp.data.local.repository.ILocalRepository
import com.fachrizalmrsln.githubuserapp.data.remote.repository.IRemoteRepository
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositories
import com.fachrizalmrsln.githubuserapp.utils.data.chunkedList
import com.fachrizalmrsln.githubuserapp.utils.strings.checkNullOrEmpty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UseCase @Inject constructor(
    private val remoteRepository: IRemoteRepository,
    private val localRepository: ILocalRepository
) : IUseCase {

    override suspend fun getSearchUser(query: String): Flow<List<SearchItemModel>> {
        val cacheData = localRepository.getSearchDataHistory(query)
        return if (cacheData.isNullOrEmpty()) getFreshData(query)
        else flow { emit(cacheData) }
    }

    override suspend fun getUserRepositories(userName: String): Flow<List<UserRepositories>> {
        val cacheData = localRepository.getRepositories(userName)
        return flow {
            emit(
                if (cacheData.isNullOrEmpty()) {
                    val freshData = remoteRepository.getUserRepositories(userName)
                    saveRepositories(freshData)
                    freshData
                }
                else cacheData
            )
        }
    }

    private suspend fun getFreshData(query: String): Flow<List<SearchItemModel>> {
        var searchResults: List<SearchItemModel>
        var detailUser: UserModel
        return flow {
            searchResults = remoteRepository.getSearchUser(query)
            val chunkedList = searchResults.chunkedList(3)
            for (i in chunkedList.indices) {
                chunkedList[i].forEach { searchItem ->
                    detailUser = remoteRepository.getDetailUser(searchItem.login)
                    chunkedList[i]
                        .filter { it.login == searchItem.login }
                        .map {
                            it.user_full_name = detailUser.name.checkNullOrEmpty()
                            it.bio = detailUser.bio.checkNullOrEmpty()
                            it.location = detailUser.location.checkNullOrEmpty()
                            it.email = detailUser.email.checkNullOrEmpty()
                            it.follower = detailUser.followers.checkNullOrEmpty()
                            it.following = detailUser.following.checkNullOrEmpty()
                        }
                }
                saveFreshDataToLocal(chunkedList[i])
                emit(chunkedList[i])
            }
        }
    }

    private suspend fun saveFreshDataToLocal(freshData: List<SearchItemModel>) {
        withContext(Dispatchers.IO) { localRepository.saveSearchDataHistory(freshData) }
    }

    private suspend fun saveRepositories(freshData: List<UserRepositories>) {
        withContext(Dispatchers.IO) { localRepository.saveRepositories(freshData) }
    }

}