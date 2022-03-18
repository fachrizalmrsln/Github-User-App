package com.fachrizalmrsln.githubuserapp.data.remote.repository

import com.fachrizalmrsln.githubuserapp.data.remote.IRemoteSource
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserModel
import com.fachrizalmrsln.githubuserapp.utils.strings.checkNullOrEmpty
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val remoteSource: IRemoteSource
) : IRemoteRepository {

    override suspend fun getSearchUser(query: String): Flow<List<SearchItemModel>> {
        var searchResults: List<SearchItemModel>
        var detailUser: UserModel
        return flow {
            searchResults = remoteSource.getSearchUser(query).items
            val resultsSize = searchResults.size
            val chunkedList = searchResults.chunked(if (resultsSize > 3) 3 else resultsSize)
            for (i in chunkedList.indices) {
                chunkedList[i].forEach { searchItem ->
                    val userName = searchItem.login
                    detailUser = remoteSource.getDetailUser(userName)
                    chunkedList[i]
                        .filter { it.login == searchItem.login }
                        .map {
                            it.user_full_name = detailUser.name.checkNullOrEmpty()
                            it.bio = detailUser.bio.checkNullOrEmpty()
                            it.location = detailUser.location.checkNullOrEmpty()
                            it.email = detailUser.email.checkNullOrEmpty()
                        }
                }
                emit(chunkedList[i])
            }
        }
    }

    override suspend fun getDetailUser(userName: String): Flow<UserModel> {
        return flow {
            emit(remoteSource.getDetailUser(userName))
        }
    }

}