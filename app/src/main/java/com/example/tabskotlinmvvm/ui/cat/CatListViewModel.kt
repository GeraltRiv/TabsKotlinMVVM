package com.example.tabskotlinmvvm.ui.cat

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.tabskotlinmvvm.model.CatDao
import com.example.tabskotlinmvvm.model.CatDog
import com.example.tabskotlinmvvm.model.data
import com.example.tabskotlinmvvm.network.PostApi
import com.example.tabskotlinmvvm.ui.BaseViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.Callable
import javax.inject.Inject

class CatListViewModel(private val catDao: CatDao) : BaseViewModel() {
//class CatListViewModel() : BaseViewModel() {

    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    val catListAdapter: CatListAdapter = CatListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        loadFromDB()
    }

    @SuppressLint("CheckResult")
    private fun loadFromDB() {
        var catList: List<CatDog>? = null
        Single.fromCallable {
            catList = catDao.all
        }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                if (catList != null) {
                    onRetrieveCatsListSuccess(catList!!)
                } else {
                    loadPostsFroInternet()
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadPostsFroInternet(){
        subscription = postApi.getCatsList("cat")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveCatsListStart() }
            .doOnTerminate { onRetrieveCatsListFinish() }
            .subscribe(
                { result ->
                    saveListToDB(result.data)
                    },
                { onRetrieveCatsListError() }
            )
    }


    @SuppressLint("CheckResult")
    fun saveListToDB(catListToSave: List<CatDog>) {
        var catList: List<CatDog>? = null
        Single.fromCallable {
            catDao.insertAll(catListToSave)
            catList = catDao.all
        }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {  onRetrieveCatsListSuccess(catList!!) })
    }


    private fun onRetrieveCatsListSuccess(catList: List<CatDog>) {
        loadingVisibility.value = View.GONE
        catListAdapter.updatePostList(catList)

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