package com.damir.android.myscore.utils

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder


object CoilImageLoader {
    private var imageLoader: ImageLoader? = null
    fun coilImageLoader(context: Context): ImageLoader {
        if(imageLoader == null) {
            imageLoader =
                ImageLoader.Builder(context)
                    .componentRegistry {
                        add(SvgDecoder(context))
                    }
                    .build()
        }
        return imageLoader as ImageLoader
    }
}