package com.fachrizalmrsln.githubuserapp.model

data class SearchItemModel(
    var id: Int,
    var avatar_url: String,
    var user_full_name: String,
    var login: String,
    var bio: String,
    var location: String,
    var email: String,
)