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
import com.example.tabskotlinmvvm.util.CAT_REQUEST_QUERY
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

    // TODO Yeah here the problem with loading from DB, because I need to return liveData from DAO
    @SuppressLint("CheckResult")
    private fun loadFromDB() {
        var catList: List<CatDog>? = null
        Single.fromCallable {
            catList = catDao.getCatDogByType(CAT_REQUEST_QUERY)
        }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                if (catList != null && catList!!.size > 0) {
                    onRetrieveCatsListSuccess(catList!!)
                } else {
                    loadPostsFroInternet()
                }
            })
    }

    private fun loadPostsFroInternet(){
        subscription = postApi.getCatsList(CAT_REQUEST_QUERY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveCatsListStart() }
            .doOnTerminate { onRetrieveCatsListFinish() }
            .subscribe(
                { result ->
                    saveListToDB(result.data)},
                { onRetrieveCatsListError() }
            )
    }


    @SuppressLint("CheckResult")
    fun saveListToDB(catListToSave: List<CatDog>) {
        var catList: List<CatDog>? = null
        catListToSave.forEach { item1 ->
            item1.type = CAT_REQUEST_QUERY
        }
        Single.fromCallable {
            catDao.insertAll(catListToSave)
            catList = catDao.all
        }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {  onRetrieveCatsListSuccess(catList!!) })
    }

    override fun onCleared() {
        super.onCleared()
        if (::subscription.isInitialized)
            subscription.dispose()
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