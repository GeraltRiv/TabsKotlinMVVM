package com.example.tabskotlinmvvm.ui.detailed

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.tabskotlinmvvm.R
import com.example.tabskotlinmvvm.di.ViewModelFactory
import com.example.tabskotlinmvvm.util.EXTRA_KEY
import com.squareup.picasso.Picasso

class DetailedFragment : Fragment() {

    companion object {
        fun newInstance() = DetailedFragment()
    }

    private lateinit var viewModel: DetailedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.detailed_fragment, container, false)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity as AppCompatActivity)).get(DetailedViewModel::class.java)
        viewModel.setExtra((activity as AppCompatActivity).intent.getIntExtra(EXTRA_KEY, -1))
        viewModel.getCatDogImage().observe(this, Observer {
            Picasso.get().load(it).into(root.findViewById<ImageView>(R.id.imageDetailedFragment))
        })
        viewModel.getCatDogTitle().observe(this, Observer {
            root.findViewById<TextView>(R.id.textDetailedFragment).text = it
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
