package com.olegion72.sunrisesunset.data.repository.parsemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SunriseSunsetResponse(
        @SerializedName("sunrise") @Expose var sunrise: String = "",
        @SerializedName("sunset") @Expose var sunset: String = "",
        @SerializedName("day_length") @Expose var dayLength: String = "",
        @SerializedName("solar_noon") @Expose var solarNoon: String = "",
        @SerializedName("civil_twilight_begin") @Expose var civilTwilightBegin: String = "",
        @SerializedName("civil_twilight_end") @Expose var civilTwilightEnd: String = "",
        @SerializedName("nautical_twilight_begin") @Expose var nauticalTwilightBegin: String = "",
        @SerializedName("nautical_twilight_end") @Expose var nauticalTwilightEnd: String = "",
        @SerializedName("astronomical_twilight_begin") @Expose var astronomicalTwilightBegin: String = "",
        @SerializedName("astronomical_twilight_end") @Expose var astronomicalTwilightEnd: String = "")