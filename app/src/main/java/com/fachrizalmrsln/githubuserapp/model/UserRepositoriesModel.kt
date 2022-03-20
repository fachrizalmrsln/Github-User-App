package com.fachrizalmrsln.githubuserapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_repositories_table")
data class UserRepositoriesModel(
    @PrimaryKey
    @ColumnInfo(name = "id_repositories_repositories")
    val id: Int,
    @ColumnInfo(name = "image_user_repositories")
    var avatar_url: String?,
    @ColumnInfo(name = "name_repositories_repositories")
    val name: String?,
    @ColumnInfo(name = "description_repositories_repositories")
    val description: String?,
    @ColumnInfo(name = "star_repositories_repositories")
    val stargazers_count: Int?,
    @ColumnInfo(name = "update_repositories_repositories")
    var updated_at: String?,
    @Embedded
    val owner: UserModel
)