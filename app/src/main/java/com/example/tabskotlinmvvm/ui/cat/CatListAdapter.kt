package com.example.tabskotlinmvvm.ui.cat

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tabskotlinmvvm.DetailedActivity

import com.example.tabskotlinmvvm.R
import com.example.tabskotlinmvvm.model.CatDog
import com.example.tabskotlinmvvm.util.EXTRA_KEY
import com.squareup.picasso.Picasso

class CatListAdapter: RecyclerView.Adapter<CatListAdapter.ViewHolder>() {

    private lateinit var catDogList:List<CatDog>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatListAdapter.ViewHolder {
        val item: View = LayoutInflater.from(parent.context).inflate(R.layout.cat_item, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: CatListAdapter.ViewHolder, position: Int) {
        holder.bind(catDogList[position])
    }

    override fun getItemCount(): Int {
        return if(::catDogList.isInitialized) catDogList.size else 0
    }

    fun updatePostList(postList:List<CatDog>){
        this.catDogList = postList
        notifyDataSetChanged()
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val v: View = item
        private val viewModel = CatViewModel();

        fun bind(catDog : CatDog) {
            viewModel.bind(catDog)
            viewModel.getCatDogTitle().observeForever { t ->
                v.findViewById<TextView>(R.id.catListTitle).text = t
            }
            viewModel.getCatDogImage().observeForever { t ->
                Picasso.get().load(t).into(v.findViewById<ImageView>(R.id.catListImage))
            }
            v.setOnClickListener(View.OnClickListener { v ->
                val intent = Intent(v.context, DetailedActivity::class.java)
                intent.putExtra(EXTRA_KEY, catDog.id_d)
                v.context.startActivity(intent)
                // To pass any data to next activity
//                intent.putExtra("keyIdentifier", value)
// start your next activity

            })
        }
    }
}
