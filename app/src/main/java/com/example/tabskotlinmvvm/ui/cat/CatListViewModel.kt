package com.example.tabskotlinmvvm.ui.cat

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.tabskotlinmvvm.model.CatDao
import com.example.tabskotlinmvvm.model.data
import com.example.tabskotlinmvvm.network.PostApi
import com.example.tabskotlinmvvm.ui.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CatListViewModel(private val catDao: CatDao) : BaseViewModel() {
//class CatListViewModel() : BaseViewModel() {

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
        subscription = postApi.getCatsList("cat")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveCatsListStart() }
            .doOnTerminate { onRetrieveCatsListFinish() }
            .subscribe(
                { result -> onRetrieveCatsListSuccess(result)
                    Log.d("Jack", "Result + " + result.toString())},
                { onRetrieveCatsListError() }
            )
    }

    private fun onRetrieveCatsListSuccess(catList: data?) {
        loadingVisibility.value = View.GONE
        catListAdapter.updatePostList(catList!!.data)
    }

//    private fun loadPosts() {
//        subscription = Observable.fromCallable { catDao.all }
//            .concatMap { dbCatList ->
//                if (dbCatList.isEmpty())
//                    postApi.getCatsList().concatMap { apiPostList ->
//                        catDao.insertAll(*apiPostList.toTypedArray())
//                        Observable.just(apiPostList)
//                    }
//                else
//                    Observable.just(dbCatList)
//            }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { onRetrieveCatsListStart() }
//            .doOnTerminate { onRetrieveCatsListFinish() }
//            .subscribe(
//                { result -> onRetrieveCatsListSuccess(result) },
//                { onRetrieveCatsListError() }
//            )
//    }

    private fun onRetrieveCatsListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveCatsListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCatsListError() {

    }
}