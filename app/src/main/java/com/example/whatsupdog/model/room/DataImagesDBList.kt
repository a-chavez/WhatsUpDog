package com.example.whatsupdog.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.whatsupdog.model.retroFit.DataImageList

@Entity (tableName = "images_table")
data class DataImagesDBList (
    @PrimaryKey val mBreed:String,
     val mImage: List<DataImageList>
    //  val mID:Int,
    //  val mBreed:String,
    //  val mImageUrl:String,
    //  val mStatus: Boolean
)