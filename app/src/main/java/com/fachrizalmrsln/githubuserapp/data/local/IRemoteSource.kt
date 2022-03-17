package com.fachrizalmrsln.githubuserapp.data.local

import com.fachrizalmrsln.githubuserapp.model.SearchModel
import com.fachrizalmrsln.githubuserapp.model.UserModel
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
        @Path("user") userID: String
    ): UserModel?

    @GET("users/{user}/repos")
    suspend fun getUserRepository(
        @Path("user") userID: String
    ): List<UserModel?>?

}