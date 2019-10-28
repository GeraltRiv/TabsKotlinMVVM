package com.example.tabskotlinmvvm.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CatDao {
    @get:Query("SELECT * FROM catdog")
    val all: List<CatDog>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(catDogs: List<CatDog>)

    @Query("SELECT * from catdog where id_d=:id")
    fun getCat(id: Int): CatDog

    @Query("SELECT * from catdog where type=:type")
    fun getCatDogByType(type: String):  List<CatDog>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChecked(catDog: CatDog)
}