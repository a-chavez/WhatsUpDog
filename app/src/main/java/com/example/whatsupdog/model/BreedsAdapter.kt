package com.example.whatsupdog.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsupdog.R
import kotlinx.android.synthetic.main.breeds.view.*

class BreedsAdapter(var mPassBreeds: Breeds) :RecyclerView.Adapter<BreedsAdapter.TaskViewHolder>() {

    private var dataList = emptyList<String>()

    fun updateListBreeds (mDataList: List<String>){
        dataList = mDataList
        notifyDataSetChanged()
    }

    inner class TaskViewHolder (itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val breedsName = itemView.tvBreed
        val itemView = itemView.setOnClickListener(this)

        override fun onClick(p0: View?) {
           mPassBreeds.passBreeds(dataList[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.breeds,parent,false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val mBreeds = dataList[position]
        holder.breedsName.text = mBreeds

    }

    override fun getItemCount() = dataList.size

    interface Breeds{
        fun passBreeds(mBreeds: String)
    }
}