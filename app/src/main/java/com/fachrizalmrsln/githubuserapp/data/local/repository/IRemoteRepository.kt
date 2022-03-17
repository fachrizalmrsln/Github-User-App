package com.fachrizalmrsln.githubuserapp.data.local.repository

import com.fachrizalmrsln.githubuserapp.model.SearchModel
import kotlinx.coroutines.flow.Flow

interface IRemoteRepository {

    suspend fun getSearchUser(query: String): Flow<SearchModel>

}