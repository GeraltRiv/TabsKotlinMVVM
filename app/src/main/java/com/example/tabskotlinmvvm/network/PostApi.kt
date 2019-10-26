package com.example.tabskotlinmvvm.network

import io.reactivex.Observable
import retrofit2.http.GET

interface PostApi {

    @GET("/cat")
    fun getCatsList(): Observable<List<Object>>
    @GET("/dog")
    fun getDogsList(): Observable<List<Object>>
}