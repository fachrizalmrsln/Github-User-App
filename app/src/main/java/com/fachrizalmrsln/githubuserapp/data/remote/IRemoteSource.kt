package com.fachrizalmrsln.githubuserapp.data.remote

import com.fachrizalmrsln.githubuserapp.model.SearchModel
import com.fachrizalmrsln.githubuserapp.model.UserModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositories
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRemoteSource {

    @GET("search/users")
    suspend fun getSearchUser(
        @Query("q") query: String
    ): SearchModel

    @GET("users/{user}")
    suspend fun getDetailUser(
        @Path("user") userName: String
    ): UserModel

    @GET("users/{user}/repos")
    suspend fun getUserRepository(
        @Path("user") userID: String
    ): List<UserRepositories>

}