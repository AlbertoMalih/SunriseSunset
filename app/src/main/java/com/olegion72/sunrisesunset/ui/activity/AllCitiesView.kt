package com.olegion72.sunrisesunset.ui.activity

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AllCitiesView : BaseLocationView {
    fun showCityName(cityName: CharSequence)
}