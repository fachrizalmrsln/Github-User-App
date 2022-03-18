package com.fachrizalmrsln.githubuserapp.model

data class UserRepositories(
    val id: Int,
    var avatar_url: String?,
    val name: String?,
    val description: String?,
    val stargazers_count: Int?,
    var updated_at: String?,
    val owner: UserModel
)