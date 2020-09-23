package com.example.whatsupdog.model.retroFit


import com.google.gson.annotations.SerializedName

data class DataImageList(
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("status")
    val status: String
)