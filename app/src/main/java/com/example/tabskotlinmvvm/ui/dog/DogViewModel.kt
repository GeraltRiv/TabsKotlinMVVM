package com.example.tabskotlinmvvm.ui.dog

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tabskotlinmvvm.model.data
import com.example.tabskotlinmvvm.network.PostApi
import com.example.tabskotlinmvvm.ui.BaseViewModel
import com.example.tabskotlinmvvm.ui.cat.CatListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DogViewModel : BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    val catListAdapter: CatListAdapter = CatListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadPosts(){
        subscription = postApi.getCatsList("dog")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveCatsListStart() }
            .doOnTerminate { onRetrieveCatsListFinish() }
            .subscribe(
                { result -> onRetrieveCatsListSuccess(result)
                    },
                { onRetrieveCatsListError() }
            )
    }

    private fun onRetrieveCatsListSuccess(catList: data?) {
        loadingVisibility.value = View.GONE
        catListAdapter.updatePostList(catList!!.data)
    }

    private fun onRetrieveCatsListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveCatsListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCatsListError() {

    }
}