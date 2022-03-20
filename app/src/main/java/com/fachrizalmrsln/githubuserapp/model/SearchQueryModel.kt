package com.fachrizalmrsln.githubuserapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "query_search_table")
data class SearchQueryModel(
    @PrimaryKey
    @ColumnInfo(name = "querySearchID")
    var querySearch: String
)