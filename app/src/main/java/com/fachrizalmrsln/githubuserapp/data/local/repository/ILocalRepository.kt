package com.fachrizalmrsln.githubuserapp.data.local.repository

import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.SearchQueryModel
import com.fachrizalmrsln.githubuserapp.model.SearchRoomResultModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositoriesModel

interface ILocalRepository {

    suspend fun saveSearchQuery(dataQuery: SearchQueryModel)

    suspend fun saveSearchDataHistory(dataSearch: List<SearchItemModel>)

    suspend fun saveRepositories(dataRepositories: List<UserRepositoriesModel>)

    suspend fun getSearchQuery(query: String): SearchQueryModel?

    suspend fun getSearchHistory(query: String): List<SearchRoomResultModel>

    suspend fun getRepositories(userName: String): List<UserRepositoriesModel>

}