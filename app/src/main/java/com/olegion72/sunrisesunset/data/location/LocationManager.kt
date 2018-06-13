package com.olegion72.sunrisesunset.data.location

import android.location.Location
import io.reactivex.Single

interface LocationManager {
    fun startFindCoordinates(): Single<Location>
}