package com.fachrizalmrsln.githubuserapp.data.usecase

import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositoriesModel
import kotlinx.coroutines.flow.Flow

interface IUseCase {

    suspend fun getSearchUser(query: String): Flow<List<SearchItemModel>>

    suspend fun getUserRepositories(userName: String): Flow<List<UserRepositoriesModel>>

}