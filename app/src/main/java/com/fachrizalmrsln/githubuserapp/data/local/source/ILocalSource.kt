package com.fachrizalmrsln.githubuserapp.data.local.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.SearchQueryModel
import com.fachrizalmrsln.githubuserapp.model.SearchRoomResultModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositoriesModel

@Dao
interface ILocalSource {

    @Insert(onConflict = REPLACE)
    suspend fun saveSearchQuery(dataQuery: SearchQueryModel)

    @Insert(onConflict = REPLACE)
    suspend fun saveSearchDataHistory(dataSearch: List<SearchItemModel>)

    @Insert(onConflict = REPLACE)
    suspend fun saveRepositories(dataRepositories: List<UserRepositoriesModel>)

    @Query("SELECT * FROM query_search_table WHERE querySearchID = :query")
    suspend fun getSearchQuery(query: String): SearchQueryModel?

    @Transaction
    @Query("SELECT * FROM query_search_table")
    fun getSearchHistory(): List<SearchRoomResultModel>

    @Query("SELECT * FROM search_history_table WHERE username_user_search LIKE '%'||:query||'%' " +
                "OR name_user_search LIKE '%'||:query||'%'")
    suspend fun getSearchDataHistory(query: String): List<SearchItemModel>

    @Query("SELECT * FROM user_repositories_table WHERE user_name_user_user = :userName")
    suspend fun getRepositories(userName: String): List<UserRepositoriesModel>

}