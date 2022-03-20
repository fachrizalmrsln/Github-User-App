package com.fachrizalmrsln.githubuserapp.utils.views

import android.app.Activity
import android.view.View
import androidx.core.content.ContextCompat

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun Activity.loadDrawable(drawableID: Int) = ContextCompat.getDrawable(this, drawableID)