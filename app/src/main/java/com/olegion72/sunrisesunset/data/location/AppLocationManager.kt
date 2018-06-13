package com.olegion72.sunrisesunset.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.olegion72.sunrisesunset.extension.CheckerPermissions
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class AppLocationManager(private val checkerPermissions: CheckerPermissions,
                         private val applicationContext: Context) : LocationManager {
    @SuppressLint("MissingPermission")
    override fun startFindCoordinates(): Single<Location> =
            Single.create<Location> { emitter ->
                if (checkerPermissions.enabledAllLocation()) {
                    with(LocationServices.getFusedLocationProviderClient(applicationContext)) {
                        requestLocationUpdates(generateLocationRequest(), object : LocationCallback() {
                            override fun onLocationResult(locationResult: LocationResult) {
                                emitter.onSuccess(locationResult.lastLocation)
                            }
                        },
                                Looper.myLooper())
                    }
                } else
                    emitter.onError(SecurityException("You does not have permissions on location"))
            }
                    .subscribeOn(AndroidSchedulers.mainThread())


    private fun generateLocationRequest(): LocationRequest {
        return LocationRequest().apply {
            numUpdates = 2
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }
}