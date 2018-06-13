package com.olegion72.sunrisesunset.data.repository

import com.olegion72.sunrisesunset.data.repository.parsemodel.ContainerSunriseSunsetResponse

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SunriseSunsetApi {
    @GET("json")
    fun loadSunriseSunset(@Query("lat") latitude: Double, @Query("lng") longitude: Double,
                          @Query("formatted") formattedType: Int = 0): Single<ContainerSunriseSunsetResponse>
}