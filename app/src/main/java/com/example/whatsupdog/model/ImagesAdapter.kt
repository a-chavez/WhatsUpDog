package com.example.whatsupdog.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whatsupdog.R
import com.example.whatsupdog.model.room.DataBreedDBList
import com.example.whatsupdog.model.room.DataImagesDBList
import kotlinx.android.synthetic.main.images.view.*

class ImagesAdapter(): RecyclerView.Adapter<ImagesAdapter.TaskViewHolder>() {

    private var dataList = emptyList<DataImagesDBList>()

    fun updateListBreedsImages (mDataList: List<DataImagesDBList>){
        dataList = mDataList
        notifyDataSetChanged()
    }

    inner class TaskViewHolder (itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val mImageUrl = itemView.imgDog1
        val itemView = itemView.setOnClickListener(this)

        override fun onClick(p0: View?) {
           // mPassImages.passData(dataList[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.images,parent,false)
        return TaskViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        val mImagesDBList:DataImagesDBList = dataList[position]
        Glide.with(holder.itemView.context).load(mImagesDBList.imageUrl).into(holder.mImageUrl)

    }

    override fun getItemCount() = dataList.size

/*
    interface ImagesBreeds{
        fun passData (mImages: DataImagesDBList)
    }
*/
}