package com.fachrizalmrsln.githubuserapp.utils.strings

fun String?.checkNullOrEmpty() = if (this.isNullOrEmpty()) "-" else this

fun String.isUserName() = "@$this"