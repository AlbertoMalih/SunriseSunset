package com.olegion72.sunrisesunset.base

abstract class BasePresenter<VIEW> {
    protected var view: VIEW? = null

    fun attachView(view: VIEW){
        this.view = view
    }

    fun detachView(){
        view = null
    }
}