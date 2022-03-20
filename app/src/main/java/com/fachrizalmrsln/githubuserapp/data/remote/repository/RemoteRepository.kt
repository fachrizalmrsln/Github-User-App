package com.fachrizalmrsln.githubuserapp.data.remote.repository

import com.fachrizalmrsln.githubuserapp.data.remote.source.IRemoteSource
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositoriesModel
import com.fachrizalmrsln.githubuserapp.utils.datetime.timeAgoTimestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val remoteSource: IRemoteSource
) : IRemoteRepository {

    override suspend fun getSearchUser(query: String): List<SearchItemModel> {
        return withContext(Dispatchers.IO) {
            remoteSource.getSearchUser(query).items
        }
    }

    override suspend fun getDetailUser(userName: String): UserModel {
        return withContext(Dispatchers.IO) {
            remoteSource.getDetailUser(userName)
        }
    }

    override suspend fun getUserRepositories(userName: String): List<UserRepositoriesModel> {
        return withContext(Dispatchers.IO) {
            val data = remoteSource.getUserRepository(userName)
            data.map {
                it.updated_at = it.updated_at.timeAgoTimestamp()
                it.avatar_url = it.owner.avatar_url
            }
            data
        }
    }

}