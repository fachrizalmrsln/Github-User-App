package com.fachrizalmrsln.githubuserapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchItemModel(
    var id: Int,
    var avatar_url: String,
    var user_full_name: String,
    var login: String,
    var bio: String,
    var location: String,
    var email: String,
    var follower: String,
    var following: String
) : Parcelable