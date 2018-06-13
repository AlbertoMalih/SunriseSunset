package com.olegion72.sunrisesunset.extension

import com.olegion72.sunrisesunset.data.repository.parsemodel.SunriseSunsetResponse
import com.olegion72.sunrisesunset.model.SunriseSunset
import org.koin.KoinContext
import org.koin.standalone.StandAloneContext

inline fun <reified T> inject() = lazy { (StandAloneContext.koinContext as KoinContext).get<T>() }

fun createSunriseSunsetFromServerResponse(response: SunriseSunsetResponse): SunriseSunset =
        SunriseSunset().apply {
            sunsetTime = getTimeFromDate(response.sunset)
            sunsetTime = getTimeFromDate(response.sunset)
            sunriseTime = getTimeFromDate(response.sunrise)
            dayLength = getTimeFromDate(response.dayLength)
        }

private fun getTimeFromDate(date: String) =
        date.split("T").getOrElse(1) { "" }.split("+").getOrElse(0) { "" }