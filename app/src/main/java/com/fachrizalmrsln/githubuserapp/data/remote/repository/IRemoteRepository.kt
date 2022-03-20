package com.fachrizalmrsln.githubuserapp.data.remote.repository

import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositories

interface IRemoteRepository {

    suspend fun getSearchUser(query: String): List<SearchItemModel>

    suspend fun getDetailUser(userName: String): UserModel

    suspend fun getUserRepositories(userName: String): List<UserRepositories>

}