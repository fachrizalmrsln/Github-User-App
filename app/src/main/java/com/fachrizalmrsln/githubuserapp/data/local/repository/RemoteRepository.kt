package com.fachrizalmrsln.githubuserapp.data.local.repository

import com.fachrizalmrsln.githubuserapp.data.local.IRemoteSource
import com.fachrizalmrsln.githubuserapp.model.SearchModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val remoteSource: IRemoteSource
) : IRemoteRepository {

    override suspend fun getSearchUser(query: String): Flow<SearchModel> {
        return flow {
            val results = remoteSource.getSearchUser(query)
            emit(results)
        }
    }

}