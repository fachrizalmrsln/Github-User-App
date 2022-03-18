package com.fachrizalmrsln.githubuserapp.data.local.repository

import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import kotlinx.coroutines.flow.Flow

interface IRemoteRepository {

    suspend fun getSearchUser(query: String): Flow<List<SearchItemModel>>

}