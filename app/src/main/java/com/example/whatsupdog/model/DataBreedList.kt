package com.example.whatsupdog.model


import com.google.gson.annotations.SerializedName

data class DataBreedList(
    @SerializedName("message") val message : List<String>,
    @SerializedName("status") val status : String
)