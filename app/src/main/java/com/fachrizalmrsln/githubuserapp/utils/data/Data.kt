package com.fachrizalmrsln.githubuserapp.utils.data

import android.app.Activity
import com.fachrizalmrsln.githubuserapp.utils.strings.checkNullOrEmpty

fun Activity.getStringExtra(key: String) = this.intent.getStringExtra(key).checkNullOrEmpty()