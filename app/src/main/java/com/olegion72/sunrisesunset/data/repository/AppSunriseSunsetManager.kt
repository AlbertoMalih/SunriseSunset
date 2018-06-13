package com.olegion72.sunrisesunset.data.repository

import com.olegion72.sunrisesunset.constants.BASE_SUNRISE_SUNSET_REST_API_URL
import com.olegion72.sunrisesunset.data.repository.parsemodel.ContainerSunriseSunsetResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AppSunriseSunsetManager : SunriseSunsetManager {
    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_SUNRISE_SUNSET_REST_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    private val sunriseSunsetApi = retrofit.create(SunriseSunsetApi::class.java)


    override fun loadSunriseSunset(latitude: Double, longitude: Double): Single<ContainerSunriseSunsetResponse> =
            sunriseSunsetApi.loadSunriseSunset(latitude, longitude)
}