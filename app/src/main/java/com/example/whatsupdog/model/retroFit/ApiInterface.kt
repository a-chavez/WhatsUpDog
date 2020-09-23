package com.example.whatsupdog.model.retroFit

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface ApiInterface {

    @GET("breeds/list")
    fun getBreedsFromApi() : Call<DataBreedList>

    @GET("breeds/{razas}/images")
    fun getImagesFromApi(@Path("razas") mRazas:String): Call<DataImageList>
}