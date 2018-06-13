package com.olegion72.sunrisesunset.data.repository.parsemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ContainerSunriseSunsetResponse(@SerializedName("results") @Expose var result: SunriseSunsetResponse)