package com.example.tabskotlinmvvm.ui.cat

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.tabskotlinmvvm.model.CatDog
import com.example.tabskotlinmvvm.ui.BaseViewModel

class CatViewModel : BaseViewModel() {

    private val catDogtTitle = MutableLiveData<String>()
    private val catDogImage = MutableLiveData<Uri>()

    fun bind(catDog: CatDog){
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