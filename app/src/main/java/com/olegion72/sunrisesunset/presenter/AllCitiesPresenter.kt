package com.olegion72.sunrisesunset.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.android.gms.location.places.Place
import com.olegion72.sunrisesunset.constants.FAIL_LOAD_INFO_BY_COORDINATES
import com.olegion72.sunrisesunset.data.repository.SunriseSunsetManager
import com.olegion72.sunrisesunset.extension.createSunriseSunsetFromServerResponse
import com.olegion72.sunrisesunset.extension.inject
import com.olegion72.sunrisesunset.model.SunriseSunset
import com.olegion72.sunrisesunset.ui.activity.AllCitiesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class AllCitiesPresenter : MvpPresenter<AllCitiesView>() {
    private val sunriseSunsetManager: SunriseSunsetManager by inject()
    private lateinit var lastPlace: Place


    private fun loadInfoByLastPlace() {
        sunriseSunsetManager.loadSunriseSunset(lastPlace.latLng.latitude, lastPlace.latLng.longitude)
                .subscribeOn(Schedulers.io())
                .doOnError { viewState.showLocationErrorMessage(FAIL_LOAD_INFO_BY_COORDINATES) }
                .map<SunriseSunset> { response -> createSunriseSunsetFromServerResponse(response.result) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { sunriseSunset -> viewState.showSunriseSunsetInfo(sunriseSunset) }
    }

    fun searchPlaceInfo(place: Place) {
        lastPlace = place
        viewState.showCityName(lastPlace.name)
        loadInfoByLastPlace()
    }
}