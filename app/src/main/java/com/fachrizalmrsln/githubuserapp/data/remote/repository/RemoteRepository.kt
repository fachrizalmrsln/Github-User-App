package com.fachrizalmrsln.githubuserapp.data.remote.repository

import com.fachrizalmrsln.githubuserapp.data.remote.source.IRemoteSource
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositoriesModel
import com.fachrizalmrsln.githubuserapp.utils.datetime.timeAgoTimestamp
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val remoteSource: IRemoteSource
) : IRemoteRepository {

    override suspend fun getSearchUser(query: String): List<SearchItemModel> {
        return remoteSource.getSearchUser(query).items
    }

    override suspend fun getDetailUser(userName: String): UserModel {
        return remoteSource.getDetailUser(userName)
    }

    override suspend fun getUserRepositories(userName: String): List<UserRepositoriesModel> {
        val data = remoteSource.getUserRepository(userName)
        data.map {
            it.updated_at = it.updated_at.timeAgoTimestamp()
            it.avatar_url = it.owner.avatar_url
        }
        return data
    }

}