package com.example.tabskotlinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tabskotlinmvvm.ui.detailed.DetailedFragment

class DetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailedFragment.newInstance())
                .commitNow()
        }
    }

}
