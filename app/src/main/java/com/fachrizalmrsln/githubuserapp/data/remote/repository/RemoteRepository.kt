package com.fachrizalmrsln.githubuserapp.data.remote.repository

import com.fachrizalmrsln.githubuserapp.data.remote.IRemoteSource
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositories
import com.fachrizalmrsln.githubuserapp.utils.data.chunkedList
import com.fachrizalmrsln.githubuserapp.utils.datetime.timeAgoTimestamp
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
            val chunkedList = searchResults.chunkedList(3)
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
                            it.follower = detailUser.followers.checkNullOrEmpty()
                            it.following = detailUser.following.checkNullOrEmpty()
                        }
                }
                emit(chunkedList[i])
            }
        }
    }

    override suspend fun getUserRepositories(userName: String): Flow<List<UserRepositories>> {
        return flow {
            val data = remoteSource.getUserRepository(userName)
            data.map {
                it.updated_at = it.updated_at.timeAgoTimestamp()
                it.avatar_url = it.owner.avatar_url
            }
            emit(data)
        }
    }

}