package com.fachrizalmrsln.githubuserapp.model

import androidx.room.ColumnInfo

data class UserModel(
    @ColumnInfo(name = "user_name_user_user")
    val login: String?,
    @ColumnInfo(name = "image_user_user")
    val avatar_url: String?,
    @ColumnInfo(name = "bio_user_user")
    val bio: String?,
    @ColumnInfo(name = "email_user_user")
    val email: String?,
    @ColumnInfo(name = "followers_user_user")
    val followers: Int?,
    @ColumnInfo(name = "following_user_user")
    val following: Int?,
    @ColumnInfo(name = "location_user_user")
    val location: String?,
    @ColumnInfo(name = "name_user_user")
    val name: String?
)