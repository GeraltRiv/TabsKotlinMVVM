package com.example.tabskotlinmvvm.di

import com.example.tabskotlinmvvm.ui.cat.CatViewModel
import com.example.tabskotlinmvvm.ui.dog.DogViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(catViewModel: CatViewModel)
    fun inject(dogViewModel: DogViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}