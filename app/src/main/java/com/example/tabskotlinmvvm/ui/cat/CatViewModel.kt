package com.example.tabskotlinmvvm.ui.cat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tabskotlinmvvm.network.PostApi
import com.example.tabskotlinmvvm.ui.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CatViewModel : BaseViewModel() {

    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    private val _text = MutableLiveData<String>().apply {
        value = "This is Cats Fragment"
    }
    val text: LiveData<String> = _text

    init{
        loadPosts()
    }
    private fun loadPosts(){
        subscription = postApi.getCatsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveCatsListStart() }
            .doOnTerminate { onRetrieveCatsListFinish() }
            .subscribe(
                { onRetrieveCatsListSuccess() },
                { onRetrieveCatsListError() }
            )
    }

    private fun onRetrieveCatsListStart(){

    }

    private fun onRetrieveCatsListFinish(){

    }

    private fun onRetrieveCatsListSuccess(){

    }

    private fun onRetrieveCatsListError(){

    }
}