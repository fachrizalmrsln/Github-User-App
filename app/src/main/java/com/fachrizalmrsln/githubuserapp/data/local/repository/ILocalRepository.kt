package com.fachrizalmrsln.githubuserapp.data.local.repository

import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositories

interface ILocalRepository {

    suspend fun saveSearchDataHistory(dataSearch: List<SearchItemModel>)

    suspend fun saveRepositories(dataRepositories: UserRepositories)

    suspend fun getSearchDataHistory(query: String): List<SearchItemModel>

    suspend fun getRepositories(): List<UserRepositories>

}