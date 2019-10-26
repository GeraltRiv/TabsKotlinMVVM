package com.example.tabskotlinmvvm.ui.dog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tabskotlinmvvm.R

class DogFragment : Fragment() {

    private lateinit var dogViewModel: DogViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dogViewModel =
            ViewModelProviders.of(this).get(DogViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dog_view, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        dogViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        return root
    }
}