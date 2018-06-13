package com.olegion72.sunrisesunset.presenter

import android.content.Context
import android.location.Geocoder
import android.location.Location
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.olegion72.sunrisesunset.constants.FAIL_LOAD_COORDINATES
import com.olegion72.sunrisesunset.constants.FAIL_LOAD_INFO_BY_COORDINATES
import com.olegion72.sunrisesunset.data.location.LocationManager
import com.olegion72.sunrisesunset.data.repository.SunriseSunsetManager
import com.olegion72.sunrisesunset.data.repository.parsemodel.ContainerSunriseSunsetResponse
import com.olegion72.sunrisesunset.extension.CheckerPermissions
import com.olegion72.sunrisesunset.extension.createSunriseSunsetFromServerResponse
import com.olegion72.sunrisesunset.extension.inject
import com.olegion72.sunrisesunset.model.SunriseSunset
import com.olegion72.sunrisesunset.ui.activity.CurrentCityView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

@InjectViewState
class CurrentCityPresenter : MvpPresenter<CurrentCityView>() {
    private val checkerPermissions: CheckerPermissions by inject()
    private val locationManager: LocationManager by inject()
    private val sunriseSunsetManager: SunriseSunsetManager by inject()
    private val applicationContext: Context by inject()


    fun startSearchLocationIfPossible() {
        if (checkerPermissions.enabledAllLocation()) {
            startFindCoordinates()
        } else {
            viewState.showMessageOnNotFoundPermissions()
        }
    }

    private fun startFindCoordinates() {
        locationManager.startFindCoordinates()
                .doOnSuccess { location -> viewState.showCurrentCity(getCityFromLocation(location)) }
                .doOnError { viewState.showLocationErrorMessage(FAIL_LOAD_COORDINATES) }
                .flatMap<ContainerSunriseSunsetResponse> {
                    sunriseSunsetManager.loadSunriseSunset(it.latitude, it.longitude)
                            .subscribeOn(Schedulers.io())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { viewState.showLocationErrorMessage(FAIL_LOAD_INFO_BY_COORDINATES) }
                .map<SunriseSunset> { response -> createSunriseSunsetFromServerResponse(response.result) }
                .subscribe { sunriseSunset -> viewState.showSunriseSunsetInfo(sunriseSunset) }
    }

    private fun getCityFromLocation(location: Location) =
            Geocoder(applicationContext, Locale.getDefault()).getFromLocation(location.latitude, location.longitude, 1)[0].locality
}