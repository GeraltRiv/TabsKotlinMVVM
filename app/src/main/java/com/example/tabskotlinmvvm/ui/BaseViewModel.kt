package com.example.tabskotlinmvvm.ui

import androidx.lifecycle.ViewModel
import com.example.tabskotlinmvvm.di.DaggerViewModelInjector
import com.example.tabskotlinmvvm.di.NetworkModule
import com.example.tabskotlinmvvm.di.ViewModelInjector
import com.example.tabskotlinmvvm.ui.cat.CatViewModel
import com.example.tabskotlinmvvm.ui.dog.DogViewModel

abstract class BaseViewModel : ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
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
            is CatViewModel -> injector.inject(this)
            is DogViewModel -> injector.inject(this)
        }
    }
}