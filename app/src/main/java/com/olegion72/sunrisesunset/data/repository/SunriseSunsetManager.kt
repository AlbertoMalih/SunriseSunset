package com.olegion72.sunrisesunset.data.repository

import com.olegion72.sunrisesunset.data.repository.parsemodel.ContainerSunriseSunsetResponse
import io.reactivex.Single

interface SunriseSunsetManager {
    fun loadSunriseSunset(latitude: Double, longitude: Double): Single<ContainerSunriseSunsetResponse>
}