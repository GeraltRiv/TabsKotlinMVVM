package com.example.tabskotlinmvvm.di

import com.example.tabskotlinmvvm.model.DBHelper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface DBHelperInjector {

    fun inject(dbHelper: DBHelper)

    @Component.Builder
    interface Builder {
        fun build(): DBHelperInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}