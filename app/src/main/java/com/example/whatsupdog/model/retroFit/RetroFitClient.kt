package com.example.whatsupdog.model.retroFit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitClient {

    companion object{
        private const val  URL_BASE = "https://dog.ceo/api/"

        fun getRetrofitClient() : ApiInterface {
            val mRetrofitClient = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofitClient.create(ApiInterface::class.java)
        }

    }

}