package com.fachrizalmrsln.githubuserapp.model

data class SearchModel(
    val incomplete_results: Boolean,
    val items: List<SearchItemModel>,
    val total_count: Int
)