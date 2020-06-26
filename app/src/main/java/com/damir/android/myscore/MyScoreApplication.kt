package com.damir.android.myscore

import android.app.Application
import android.content.Context
import android.util.Log
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.damir.android.myscore.di.appModule
import com.damir.android.myscore.di.domainModule
import com.damir.android.myscore.di.networkModule
import com.damir.android.myscore.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyScoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyScoreApplication)
            modules(listOf(networkModule, domainModule, presentationModule))
        }
    }
}
