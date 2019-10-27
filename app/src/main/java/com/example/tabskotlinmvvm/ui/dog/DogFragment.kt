package com.example.tabskotlinmvvm.ui.dog

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabskotlinmvvm.R
import com.example.tabskotlinmvvm.util.LIST_STATE_KEY



class DogFragment : Fragment() {

    private lateinit var mListState: Parcelable
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var dogViewModel: DogViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dogViewModel =
            ViewModelProviders.of(this).get(DogViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dog_view, container, false)

        val recyclerViev = root.findViewById<RecyclerView>(R.id.dog_list)
        mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerViev.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = dogViewModel.catListAdapter
        }

//        recyclerViev.addOnItemTouchListener(object : View.OnTouchListener {
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                when (event?.action) {
//                    MotionEvent.ACTION_DOWN -> Log.d()//Do Something
//                }
//
//                return v?.onTouchEvent(event) ?: true
//            }
//        })
        val progressBar = root.findViewById<ProgressBar>(R.id.progress_bar_dog)
        dogViewModel.loadingVisibility.observe(this, Observer {
            progressBar.visibility = it
        })
        return root
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        // Save list state
//        mListState = mLayoutManager.onSaveInstanceState()!!;
//        outState.putParcelable(LIST_STATE_KEY, mListState);
//    }
//
//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        // Retrieve list state and list/item positions
//        if (savedInstanceState != null)
//            mListState = savedInstanceState.getParcelable(LIST_STATE_KEY)!!
//    }
//
//    override fun onResume() {
//        super.onResume()
//        if (mListState != null) {
//            mLayoutManager.onRestoreInstanceState(mListState);
//        }
//    }
}