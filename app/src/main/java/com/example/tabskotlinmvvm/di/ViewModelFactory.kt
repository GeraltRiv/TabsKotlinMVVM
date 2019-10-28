package com.example.tabskotlinmvvm.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.tabskotlinmvvm.model.database.AppDatabase
import com.example.tabskotlinmvvm.ui.cat.CatListViewModel
import com.example.tabskotlinmvvm.ui.detailed.DetailedViewModel
import com.example.tabskotlinmvvm.ui.dog.DogViewModel

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val db =
            Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "posts")
                .build()
        if (modelClass.isAssignableFrom(CatListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatListViewModel(db.catDao()) as T
        } else if (modelClass.isAssignableFrom(DogViewModel::class.java)) {
            return DogViewModel(db.catDao()) as T
        } else if (modelClass.isAssignableFrom(DetailedViewModel::class.java)) {
            return DetailedViewModel(db.catDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}