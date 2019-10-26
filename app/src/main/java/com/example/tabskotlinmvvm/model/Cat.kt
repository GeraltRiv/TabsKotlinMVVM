package com.example.tabskotlinmvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class data (
    val data: List<Cat>
)
@Entity
data class Cat(
    @field:PrimaryKey
    val id: Int,
    val title: String,
    val url: String
)