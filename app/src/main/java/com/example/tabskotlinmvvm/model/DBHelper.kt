package com.example.tabskotlinmvvm.model

import android.annotation.SuppressLint
import com.example.tabskotlinmvvm.network.PostApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

// TODO Yeah here the problem with loading from DB, because I need to return liveData from DAO
class DBHelper constructor(private val catDao: CatDao): BaseDBHelper() {

    @Inject
    lateinit var postApi: PostApi
}