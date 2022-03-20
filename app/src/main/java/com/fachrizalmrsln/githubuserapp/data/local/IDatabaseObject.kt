package com.fachrizalmrsln.githubuserapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositories

@Dao
interface IDatabaseObject {

    @Insert(onConflict = IGNORE)
    suspend fun saveSearchHistory(dataSearch: SearchItemModel)

    @Insert(onConflict = IGNORE)
    suspend fun saveRepositories(dataRepositories: UserRepositories)

    @Query("SELECT * FROM search_history_table")
    suspend fun getSearchHistory(): List<SearchItemModel>

    @Query("SELECT * FROM user_repositories_table")
    suspend fun getRepositories(): List<UserRepositories>

}