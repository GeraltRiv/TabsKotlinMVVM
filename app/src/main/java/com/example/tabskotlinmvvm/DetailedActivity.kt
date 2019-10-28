package com.example.tabskotlinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tabskotlinmvvm.ui.detailed.DetailedFragment
import com.example.tabskotlinmvvm.util.EXTRA_KEY

class DetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_activity)
//        val s = intent.getIntExtra(EXTRA_KEY, -1)
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailedFragment.newInstance())
                .commitNow()

    }

}
