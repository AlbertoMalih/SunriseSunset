package com.olegion72.sunrisesunset.extension

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat

class CheckerPermissions(private val context: Context) {
    fun enabledGPS() =
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    fun enabledWiFiLocation() =
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    fun enabledAllLocation() =
            enabledGPS() && enabledWiFiLocation()
}