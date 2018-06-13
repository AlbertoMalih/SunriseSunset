package com.olegion72.sunrisesunset.ui.activity

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.olegion72.sunrisesunset.R
import com.olegion72.sunrisesunset.constants.LOCATION_PERMISSIONS_REQUEST
import com.olegion72.sunrisesunset.model.SunriseSunset
import com.olegion72.sunrisesunset.presenter.CurrentCityPresenter
import kotlinx.android.synthetic.main.activity_current_city.*
import kotlinx.android.synthetic.main.block_info_on_sunrise_sunset.*


class CurrentCityActivity : BaseLocationActivity(), CurrentCityView {
    @InjectPresenter()
    lateinit var presenter: CurrentCityPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_city)

        chooseCity.setOnClickListener { startAllCitiesActivity() }

        if (savedInstanceState == null) {
            presenter.startSearchLocationIfPossible()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            LOCATION_PERMISSIONS_REQUEST -> {
                if (grantResults.size == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    startFindLocationInfo()
                } else {
                    Toast.makeText(this, getString(R.string.permissions_not_found), Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    override fun showMessageOnNotFoundPermissions() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), LOCATION_PERMISSIONS_REQUEST)
    }

    override fun showCurrentCity(cityName: String) {
        currentCityName.text = cityName
    }

    override fun showSunriseSunsetInfo(sunriseSunset: SunriseSunset) {
        sunset.text = StringBuilder(getString(R.string.sunset)).append(": ").append(sunriseSunset.sunsetTime)
        sunrise.text = StringBuilder(getString(R.string.sunrise)).append(": ").append(sunriseSunset.sunriseTime)
    }


    private fun startAllCitiesActivity() {
        startActivity(Intent(this, AllCitiesActivity::class.java))
    }

    private fun startFindLocationInfo() {
        presenter.startSearchLocationIfPossible()
    }
}