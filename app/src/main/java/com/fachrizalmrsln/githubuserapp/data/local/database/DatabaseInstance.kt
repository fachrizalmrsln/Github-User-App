package com.fachrizalmrsln.githubuserapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fachrizalmrsln.githubuserapp.data.local.IDatabaseObject
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositories

@Database(entities = [SearchItemModel::class, UserRepositories::class], version = 1)
abstract class DatabaseInstance : RoomDatabase() {

    abstract fun databaseObject(): IDatabaseObject

}