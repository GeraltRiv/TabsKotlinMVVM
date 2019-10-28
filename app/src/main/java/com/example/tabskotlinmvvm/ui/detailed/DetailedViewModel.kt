package com.example.tabskotlinmvvm.ui.detailed

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.tabskotlinmvvm.model.CatDao
import com.example.tabskotlinmvvm.model.CatDog
import com.example.tabskotlinmvvm.ui.BaseViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class DetailedViewModel(private val catDao: CatDao) : BaseViewModel() {

    private var idOfCatDog: Int = 0
    private lateinit var catDog: CatDog
    private val catDogtTitle = MutableLiveData<String>()
    private val catDogImage = MutableLiveData<Uri>()

    fun setExtra(get: Int) {
        this.idOfCatDog = get
        getCatDogFromDB()
    }

    private fun getCatDogFromDB() {
        var catDog: CatDog? = null
        Single.fromCallable {
            catDog = catDao.getCat(idOfCatDog)
        }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer { bind(catDog!!) } )
    }

    private fun bind(catDog: CatDog) {
        catDogtTitle.value = catDog.title
        catDogImage.value = Uri.parse(catDog.url)
    }

    fun getCatDogTitle():MutableLiveData<String>{
        return catDogtTitle
    }

    fun getCatDogImage():MutableLiveData<Uri>{
        return catDogImage
    }


}
