package com.example.tabskotlinmvvm.model

import com.example.tabskotlinmvvm.di.DBHelperInjector
import com.example.tabskotlinmvvm.di.DaggerDBHelperInjector
import com.example.tabskotlinmvvm.di.NetworkModule


abstract class BaseDBHelper{
    private val injector: DBHelperInjector = DaggerDBHelperInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is DBHelper -> injector.inject(this)
        }
    }
}