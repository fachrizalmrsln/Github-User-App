package com.fachrizalmrsln.githubuserapp.model

data class SearchModel(
    val incomplete_results: Boolean,
    val items: List<UserModel>,
    val total_count: Int
)