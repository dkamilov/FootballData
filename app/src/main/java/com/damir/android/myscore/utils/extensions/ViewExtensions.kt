package com.damir.android.myscore.utils.extensions

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import coil.request.LoadRequest
import com.damir.android.myscore.utils.CoilImageLoader

fun ImageView.loadSvg(url: String) {
    val request = LoadRequest.Builder(this.context)
        .data(url)
        .target(this)
        .build()
    CoilImageLoader
        .coilImageLoader(this.context.applicationContext)
        .execute(request)
}

fun TextView.setTextColorExt(colorRes: Int) {
    this.setTextColor(ContextCompat.getColor(this.context, colorRes))
}