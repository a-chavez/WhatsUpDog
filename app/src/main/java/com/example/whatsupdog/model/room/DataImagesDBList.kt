package com.example.whatsupdog.model.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.whatsupdog.model.retroFit.DataImageList

@Entity (tableName = "images_table")

data class DataImagesDBList (
    @PrimaryKey (autoGenerate = true)
    @NonNull
    val id:Int=0, // para que no lo exija
      val breed:String,
      val imageUrl:String,
      val status: Boolean
    )