package com.fachrizalmrsln.githubuserapp.model

data class UserModel(
    val avatar_url: String?,
    val bio: String?,
    val email: String?,
    val followers: Int?,
    val following: Int?,
    val location: String?,
    val name: String?
)