package com.fachrizalmrsln.githubuserapp.data.local.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositories

@Dao
interface ILocalSource {

    @Insert(onConflict = IGNORE)
    suspend fun saveSearchDataHistory(dataSearch: List<SearchItemModel>)

    @Insert(onConflict = IGNORE)
    suspend fun saveRepositories(dataRepositories: UserRepositories)

    @Query("SELECT * FROM search_history_table WHERE username_user_search LIKE '%'||:query||'%' " +
            "OR name_user_search LIKE '%'||:query||'%'")
    suspend fun getSearchDataHistory(query: String): List<SearchItemModel>

    @Query("SELECT * FROM user_repositories_table")
    suspend fun getRepositories(): List<UserRepositories>

}