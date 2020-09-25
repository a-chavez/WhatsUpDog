package com.example.whatsupdog.model.retroFit

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface ApiInterface {

    @GET("breeds/list") //endPoint
    fun getBreedsFromApi() : Call<DataBreedList>

    @GET("breed/{razas}/images") //endPoint
    fun getImagesFromApi(@Path("razas") mRazas:String): Call<DataImageList>
}