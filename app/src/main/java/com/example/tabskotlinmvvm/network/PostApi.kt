package com.example.tabskotlinmvvm.network

import com.example.tabskotlinmvvm.model.data
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("/xim/api.php")
    fun getCatsList(@Query("query") string: String): Observable<data>
    @GET("/dog/")
    fun getDogsList(): Observable<List<Object>>
}