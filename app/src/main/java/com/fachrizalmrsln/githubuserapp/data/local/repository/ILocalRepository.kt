package com.fachrizalmrsln.githubuserapp.data.local.repository

import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositoriesModel

interface ILocalRepository {

    suspend fun saveSearchDataHistory(dataSearch: List<SearchItemModel>)

    suspend fun saveRepositories(dataRepositories: List<UserRepositoriesModel>)

    suspend fun getSearchDataHistory(query: String): List<SearchItemModel>

    suspend fun getRepositories(userName: String): List<UserRepositoriesModel>

}