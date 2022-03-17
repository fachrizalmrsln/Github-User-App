package com.fachrizalmrsln.githubuserapp.utils.image

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.fachrizalmrsln.githubuserapp.R
import de.hdodenhof.circleimageview.CircleImageView

fun CircleImageView.loadImage(context: Context, urlImage: String) {
    Glide.with(context)
        .load(urlImage)
        .placeholder(context.drawable(R.drawable.app_icon))
        .into(this)
}

fun Context.drawable(value: Int): Drawable? {
    return ContextCompat.getDrawable(this, value)
}