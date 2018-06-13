package com.olegion72.sunrisesunset.ui.activity

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CurrentCityView : BaseLocationView {
    fun showCurrentCity(cityName: String)
}