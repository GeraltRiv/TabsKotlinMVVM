package com.example.tabskotlinmvvm.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CatDao {
    @get:Query("SELECT * FROM catdog")
    val all: List<CatDog>

    @Insert
    fun insertAll(vararg posts: CatDog)
}