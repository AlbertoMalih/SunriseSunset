package com.olegion72.sunrisesunset

import android.app.Application
import com.olegion72.sunrisesunset.di.imagesModule
import org.koin.android.ext.android.startKoin


open class SunriseSunsetApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(imagesModule))
    }
}