package com.fachrizalmrsln.githubuserapp.utils.data

import android.app.Activity
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel

fun Activity.getParcelable(key: String) = this.intent.getParcelableExtra<SearchItemModel>(key)

fun <T> List<T>.chunkedList(chuckedBy: Int): List<List<T>> {
    return chunked(if (this.size > chuckedBy) chuckedBy else this.size)
}