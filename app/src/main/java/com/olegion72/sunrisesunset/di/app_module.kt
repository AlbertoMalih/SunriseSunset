package com.olegion72.sunrisesunset.di

import android.app.job.JobScheduler
import android.content.Context
import android.net.ConnectivityManager
import com.olegion72.sunrisesunset.data.location.AppLocationManager
import com.olegion72.sunrisesunset.data.location.LocationManager
import com.olegion72.sunrisesunset.data.repository.AppSunriseSunsetManager
import com.olegion72.sunrisesunset.data.repository.SunriseSunsetManager
import com.olegion72.sunrisesunset.extension.CheckerPermissions
import org.koin.dsl.module.applicationContext

val imagesModule = applicationContext {
    bean { get<Context>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
    bean { get<Context>().getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler }
    bean { AppLocationManager(get(), get()) as LocationManager }
    bean { AppSunriseSunsetManager() as SunriseSunsetManager }
    bean { CheckerPermissions(get()) }
}