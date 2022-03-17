package com.fachrizalmrsln.githubuserapp.data.local

import com.fachrizalmrsln.githubuserapp.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRemoteSource {

    @GET("users")
    suspend fun getAllUsers(): List<User?>?

    @GET("search/users")
    suspend fun getSearchUser(
        @Query("q") query: String
    ): List<User?>?

    @GET("users/{user}")
    suspend fun getDetailUser(
        @Path("user") userID: String
    ): User?

    @GET("users/{user}/repos")
    suspend fun getUserRepository(
        @Path("user") userID: String
    ): List<User?>?

}