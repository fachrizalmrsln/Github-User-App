package com.fachrizalmrsln.githubuserapp.utils.strings

fun String?.checkNullOrEmpty() = if (this.isNullOrEmpty()) "-" else this

fun String?.isUserName() = if (this.isNullOrEmpty()) "-" else "@$this"

fun Int?.checkNullOrEmpty() = this?.toString() ?: "0"