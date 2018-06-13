package com.olegion72.sunrisesunset.ui.activity

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.olegion72.sunrisesunset.model.SunriseSunset

@StateStrategyType(OneExecutionStateStrategy::class)
interface BaseLocationView : MvpView {
    fun showMessageOnNotFoundPermissions()

    fun showLocationErrorMessage(messageCode: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showSunriseSunsetInfo(sunriseSunset: SunriseSunset)
}