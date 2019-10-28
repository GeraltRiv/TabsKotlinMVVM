package com.example.tabskotlinmvvm.di

import com.example.tabskotlinmvvm.ui.cat.CatListViewModel
import com.example.tabskotlinmvvm.ui.dog.DogViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(catListViewModel: CatListViewModel)
    fun inject(dogViewModel: DogViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}