package com.olegion72.sunrisesunset.ui.activity

import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.olegion72.sunrisesunset.R
import com.olegion72.sunrisesunset.constants.FAIL_LOAD_COORDINATES
import com.olegion72.sunrisesunset.constants.FAIL_LOAD_INFO_BY_COORDINATES

abstract class BaseLocationActivity : MvpAppCompatActivity(), BaseLocationView {

    override fun showLocationErrorMessage(messageCode: Int) {
        showMessageByToast(chooseMessage(messageCode)
        )
    }

    private fun chooseMessage(messageCode: Int): Int {
        return when (messageCode) {
            FAIL_LOAD_COORDINATES -> R.string.failLoadCoordinates
            FAIL_LOAD_INFO_BY_COORDINATES -> R.string.failLoadInfo
            else -> throw IllegalArgumentException("Invalid message code")
        }
    }

    private fun showMessageByToast(messageResCode: Int) {
        Toast.makeText(this, messageResCode, Toast.LENGTH_SHORT).show()
    }
}