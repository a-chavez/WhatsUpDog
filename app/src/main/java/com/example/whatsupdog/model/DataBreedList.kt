package com.example.whatsupdog.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "breeds_table")
data class DataBreedList(
    @PrimaryKey val id:String,
    @SerializedName("message") val message : List<String>,
    @SerializedName("status") val status : String
)