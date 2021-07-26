package com.smartmobilefactory.tvmazeapp.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.smartmobilefactory.tvmazeapp.R

fun ImageView.loadImage(uri: String?) {
    val options = RequestOptions()
        .error(R.drawable.error)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}