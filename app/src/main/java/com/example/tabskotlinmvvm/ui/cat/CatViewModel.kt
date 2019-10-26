package com.example.tabskotlinmvvm.ui.cat

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.tabskotlinmvvm.model.Cat
import com.example.tabskotlinmvvm.ui.BaseViewModel

class CatViewModel : BaseViewModel() {

    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<Uri>()

    fun bind(cat: Cat){
        postTitle.value = cat.title

    }

    fun getPostTitle():MutableLiveData<String>{
        return postTitle
    }

    fun getPostImage():MutableLiveData<Uri>{
        return postBody
    }
}