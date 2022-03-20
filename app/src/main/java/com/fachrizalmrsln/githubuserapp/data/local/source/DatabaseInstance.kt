package com.fachrizalmrsln.githubuserapp.data.local.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositoriesModel

@Database(entities = [SearchItemModel::class, UserRepositoriesModel::class], version = 1)
abstract class DatabaseInstance : RoomDatabase() {

    abstract fun databaseObject(): ILocalSource

}