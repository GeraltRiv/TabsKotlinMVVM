package com.example.tabskotlinmvvm.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.tabskotlinmvvm.model.database.AppDatabase
import com.example.tabskotlinmvvm.ui.cat.CatListViewModel
import com.example.tabskotlinmvvm.ui.dog.DogViewModel

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatListViewModel::class.java)) {
            val db =
                Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "posts")
                    .build()
            @Suppress("UNCHECKED_CAST")
            return CatListViewModel(db.postDao()) as T
        } else if (modelClass.isAssignableFrom(DogViewModel::class.java)) {

        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}