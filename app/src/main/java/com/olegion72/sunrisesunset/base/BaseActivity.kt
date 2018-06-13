package com.olegion72.sunrisesunset.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewId())
        attachViewToPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        detachViewFromPresenter()
    }

    abstract fun attachViewToPresenter()
    abstract fun detachViewFromPresenter()
    abstract fun viewId(): Int
}