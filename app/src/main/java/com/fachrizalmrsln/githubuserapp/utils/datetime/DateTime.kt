package com.fachrizalmrsln.githubuserapp.utils.datetime

import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.getInstance
import kotlin.math.abs

fun String?.timeAgoTimestamp(): String {
    return if (!this.isNullOrEmpty()) {
        val timeStamp = formatDateAndTime()
        val seconds = abs(getInstance().timeInMillis - timeStamp) / 1000
        println(getInstance().timeInMillis.convertTimeStamp())
        val minutes = (seconds / 60).toInt()
        val hours = minutes / 60
        val days = hours / 24
        when {
            seconds < 60 -> "Updated $seconds seconds ago"
            minutes < 60 -> "Updated $minutes minutes ago"
            hours < 24 -> "Updated $hours hours ago"
            days < 2 -> "Updated yesterday"
            days < 30 -> "Updated $days days ago"
            else -> "Updated at ${timeStamp.convertTimeStamp()}"
        }
    } else "-"
}

private fun String.formatDateAndTime(): Long {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.forLanguageTag("EN"))
    val parse = sdf.parse(this)
    return (parse?.time ?: this) as Long
}


private fun Long.convertTimeStamp(): String {
    return SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("EN"))
        .format(this)
}