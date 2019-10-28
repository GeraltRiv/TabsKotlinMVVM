package com.example.tabskotlinmvvm.ui.cat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabskotlinmvvm.R
import com.example.tabskotlinmvvm.di.ViewModelFactory

class CatFragment : Fragment() {

    private lateinit var catListViewModel: CatListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        catListViewModel = ViewModelProviders.of(this, ViewModelFactory(activity as AppCompatActivity)).get(CatListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cat, container, false)
        val recyclerViev = root.findViewById<RecyclerView>(R.id.cat_list)

        recyclerViev.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = catListViewModel.catListAdapter
        }

        val progressBar = root.findViewById<ProgressBar>(R.id.progress_bar)
        catListViewModel.loadingVisibility.observe(this, Observer {
            progressBar.visibility = it
        })
        return root
    }
}