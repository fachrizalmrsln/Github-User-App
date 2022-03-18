package com.fachrizalmrsln.githubuserapp.data.remote.repository

import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositories
import kotlinx.coroutines.flow.Flow

interface IRemoteRepository {

    suspend fun getSearchUser(query: String): Flow<List<SearchItemModel>>

    suspend fun getUserRepositories(userName: String): Flow<List<UserRepositories>>

}