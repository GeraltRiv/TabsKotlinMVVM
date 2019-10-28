package com.example.tabskotlinmvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class data (
    val data: List<CatDog>
)
@Entity
data class CatDog(
    @PrimaryKey(autoGenerate = true)
    val id_d: Int,
    val id: Int,
    val title: String,
    val url: String
)