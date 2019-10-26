package com.example.tabskotlinmvvm.ui.cat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.RecyclerView
import com.example.tabskotlinmvvm.R
import com.example.tabskotlinmvvm.model.Cat

class CatListAdapter: RecyclerView.Adapter<CatListAdapter.ViewHolder>() {

    private lateinit var catList:List<Cat>
    public lateinit var context: Fragment


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatListAdapter.ViewHolder {
        val item: View = LayoutInflater.from(parent.context).inflate(R.layout.cat_item, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: CatListAdapter.ViewHolder, position: Int) {
        holder.bind(catList[position])
    }

    override fun getItemCount(): Int {
        return if(::catList.isInitialized) catList.size else 0
    }

    fun updatePostList(postList:List<Cat>){
        this.catList = postList
        notifyDataSetChanged()
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item){
        private val v: View = item
        private val viewModel = CatViewModel();

        fun bind(cat : Cat) {
            viewModel.bind(cat)
            viewModel.getPostTitle().observeForever(
                { t ->  v.findViewById<TextView>(R.id.catListTitle).text = t}
            )
        }
    }
}
