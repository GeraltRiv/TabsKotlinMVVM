package com.example.tabskotlinmvvm.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tabskotlinmvvm.model.Cat
import com.example.tabskotlinmvvm.model.CatDao

@Database(entities = arrayOf(Cat::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): CatDao
}