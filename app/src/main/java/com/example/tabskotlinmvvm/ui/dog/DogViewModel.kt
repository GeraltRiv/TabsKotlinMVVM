package com.example.tabskotlinmvvm.ui.dog

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.tabskotlinmvvm.model.CatDao
import com.example.tabskotlinmvvm.model.CatDog
import com.example.tabskotlinmvvm.model.data
import com.example.tabskotlinmvvm.network.PostApi
import com.example.tabskotlinmvvm.ui.BaseViewModel
import com.example.tabskotlinmvvm.ui.cat.CatListAdapter
import com.example.tabskotlinmvvm.util.CAT_REQUEST_QUERY
import com.example.tabskotlinmvvm.util.DOG_REQUEST_QUERY
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DogViewModel(private val catDao: CatDao) : BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    val catListAdapter: CatListAdapter = CatListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        loadFromDB()
    }

    override fun onCleared() {
        super.onCleared()
        if (::subscription.isInitialized)
            subscription.dispose()
    }

    // TODO Yeah here the problem with loading from DB, because I need to return liveData from DAO
    @SuppressLint("CheckResult")
    private fun loadFromDB() {
        var dogList: List<CatDog>? = null
        Single.fromCallable {
            dogList = catDao.getCatDogByType(DOG_REQUEST_QUERY)
        }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                if (dogList != null && dogList!!.size > 0) {
                    onRetrieveCatsListSuccess(dogList!!)
                } else {
                    loadPostsFroInternet()
                }
            })
    }

    private fun loadPostsFroInternet(){
        subscription = postApi.getCatsList(DOG_REQUEST_QUERY)
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
            item1.type = DOG_REQUEST_QUERY
        }
        Single.fromCallable {
            catDao.insertAll(catListToSave)
            catList = catDao.all
        }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                Consumer {  onRetrieveCatsListSuccess(catList!!) })
    }

    private fun onRetrieveCatsListSuccess(catList: List<CatDog>?) {
        loadingVisibility.value = View.GONE
        catListAdapter.updatePostList(catList!!)
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