package com.olegion72.sunrisesunset.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.gms.location.places.AutocompleteFilter
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.olegion72.sunrisesunset.R
import com.olegion72.sunrisesunset.constants.SEARCH_CITY_ACTIVITY_BACK_CODE
import com.olegion72.sunrisesunset.model.SunriseSunset
import com.olegion72.sunrisesunset.presenter.AllCitiesPresenter
import kotlinx.android.synthetic.main.block_info_on_sunrise_sunset.*


class AllCitiesActivity : BaseLocationActivity(), AllCitiesView {
    @InjectPresenter()
    lateinit var presenter: AllCitiesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_cities)
        if (savedInstanceState == null)
            startSearchCity()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK)
            when (requestCode) {
                SEARCH_CITY_ACTIVITY_BACK_CODE -> presenter.searchPlaceInfo(PlaceAutocomplete.getPlace(this, data))
                PlaceAutocomplete.RESULT_ERROR -> showErrorOnSearchPlace(data)
            }
    }

    override fun showSunriseSunsetInfo(sunriseSunset: SunriseSunset) {
        sunset.text = StringBuilder(getString(R.string.sunset)).append(": ").append(sunriseSunset.sunsetTime)
        sunrise.text = StringBuilder(getString(R.string.sunrise)).append(": ").append(sunriseSunset.sunriseTime)
    }

    override fun showMessageOnNotFoundPermissions() {
        Toast.makeText(this, R.string.permissions_bot_found, Toast.LENGTH_LONG).show()
    }


    override fun showCityName(cityName: CharSequence) {
        currentCityName.text = cityName
    }


    private fun showErrorOnSearchPlace(data: Intent) {
        Toast.makeText(this, PlaceAutocomplete.getStatus(this, data).statusMessage, Toast.LENGTH_LONG).show()
    }

    private fun startSearchCity() {
        startActivityForResult(createIntentFilterCities(), SEARCH_CITY_ACTIVITY_BACK_CODE)
    }

    private fun createIntentFilterCities() =
            PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).setFilter(createAutocompleteCitiesFilter()).build(this)

    private fun createAutocompleteCitiesFilter(): AutocompleteFilter =
            AutocompleteFilter.Builder().setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES).build()
}
