package com.fachrizalmrsln.githubuserapp.data.local.repository

import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositories

interface ILocalRepository {

    suspend fun saveSearchHistory(dataSearch: SearchItemModel)

    suspend fun saveRepositories(dataRepositories: UserRepositories)

    suspend fun getSearchHistory(): List<SearchItemModel>

    suspend fun getRepositories(): List<UserRepositories>

}