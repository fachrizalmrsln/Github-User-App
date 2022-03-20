package com.fachrizalmrsln.githubuserapp.model

import androidx.room.Embedded
import androidx.room.Relation

data class SearchRoomResultModel(
    @Embedded val searchQuery: SearchQueryModel,
    @Relation(
        parentColumn = "querySearchID",
        entityColumn = "_querySearchID"
    )
    val items: List<SearchItemModel>
)