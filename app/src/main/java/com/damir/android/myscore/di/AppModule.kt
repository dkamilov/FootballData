package com.damir.android.myscore.di

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder
import org.koin.dsl.module

val appModule = module {
    single { coilImageLoader(get()) }
}

private fun coilImageLoader(context: Context) =
    ImageLoader.Builder(context)
        .componentRegistry {
            add(SvgDecoder(context))
        }
        .build()