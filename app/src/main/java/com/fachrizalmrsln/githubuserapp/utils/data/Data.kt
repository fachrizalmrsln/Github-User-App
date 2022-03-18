package com.fachrizalmrsln.githubuserapp.utils.data

import android.app.Activity
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel

fun Activity.getParcelable(key: String) = this.intent.getParcelableExtra<SearchItemModel>(key)