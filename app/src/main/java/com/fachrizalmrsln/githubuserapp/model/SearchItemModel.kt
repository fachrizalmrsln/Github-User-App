package com.fachrizalmrsln.githubuserapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "search_history_table")
data class SearchItemModel(
    @PrimaryKey
    @ColumnInfo(name = "id_user_search")
    var id: Int,
    @ColumnInfo(name = "picture_user_search")
    var avatar_url: String,
    @ColumnInfo(name = "name_user_search")
    var user_full_name: String,
    @ColumnInfo(name = "username_user_search")
    var login: String,
    @ColumnInfo(name = "bio_user_search")
    var bio: String,
    @ColumnInfo(name = "location_user_search")
    var location: String,
    @ColumnInfo(name = "email_user_search")
    var email: String,
    @ColumnInfo(name = "follower_user_search")
    var follower: String,
    @ColumnInfo(name = "following_user_search")
    var following: String
) : Parcelable