package com.fachrizalmrsln.githubuserapp.data.usecase

import com.fachrizalmrsln.githubuserapp.data.local.repository.ILocalRepository
import com.fachrizalmrsln.githubuserapp.data.remote.repository.IRemoteRepository
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.SearchQueryModel
import com.fachrizalmrsln.githubuserapp.model.UserModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositoriesModel
import com.fachrizalmrsln.githubuserapp.utils.data.chunkedList
import com.fachrizalmrsln.githubuserapp.utils.strings.checkNullOrEmpty
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UseCase @Inject constructor(
    private val remoteRepository: IRemoteRepository,
    private val localRepository: ILocalRepository
) : IUseCase {

    override suspend fun getSearchUser(query: String): Flow<List<SearchItemModel>> {
        val cacheData = localRepository.getSearchQuery(query)
        return if (cacheData == null) getFreshData(query)
        else {
            val data = localRepository.getSearchHistory()
            flow { data.map { emit(it.items) } }
        }
    }

    override suspend fun getUserRepositories(userName: String): Flow<List<UserRepositoriesModel>> {
        val cacheData = localRepository.getRepositories(userName)
        return flow {
            emit(
                if (cacheData.isNullOrEmpty()) {
                    val freshData = remoteRepository.getUserRepositories(userName)
                    saveRepositories(freshData)
                    freshData
                } else cacheData
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
                            it._querySearchID = query
                        }
                }
                saveFreshDataToLocal(query, chunkedList[i])
                emit(chunkedList[i])
            }
        }
    }

    private suspend fun saveFreshDataToLocal(dataQuery: String, freshData: List<SearchItemModel>) {
        localRepository.saveSearchQuery(SearchQueryModel(dataQuery))
        localRepository.saveSearchDataHistory(freshData)
    }

    private suspend fun saveRepositories(freshData: List<UserRepositoriesModel>) {
        localRepository.saveRepositories(freshData)
    }

}