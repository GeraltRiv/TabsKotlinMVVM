package com.example.tabskotlinmvvm.ui.cat

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.tabskotlinmvvm.model.CatDog
import com.example.tabskotlinmvvm.ui.BaseViewModel

class CatViewModel : BaseViewModel() {

    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<Uri>()

    fun bind(catDog: CatDog){
        postTitle.value = catDog.title
        postBody.value = Uri.parse(catDog.url)

    }

    fun getPostTitle():MutableLiveData<String>{
        return postTitle
    }

    fun getPostImage():MutableLiveData<Uri>{
        return postBody
    }
}