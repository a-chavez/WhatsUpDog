package com.example.whatsupdog.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val service = RetroFitClient.getRetrofitClient()
    val mLiveDataBreedList : MutableLiveData<List<String>> = MutableLiveData()
    val mLiveDataImageBreedList : MutableLiveData<List<String>> = MutableLiveData()

   //LaVieja1
    fun getBreedsFromServer() {
        val mCall = service.getBreedsFromApi()
        mCall.enqueue(object : Callback<DataBreedList>{
            override fun onResponse(call: Call<DataBreedList>, response: Response<DataBreedList>) {
                when(response.code()){
                    in 200..299 -> mLiveDataBreedList.postValue(response.body()?.message)
                    in 300..399 -> Log.d("ERROR 300 Breeds", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<DataBreedList>, t: Throwable) {
                Log.e("Error en Breeds API", t.message.toString())
            }

        })
    }

    //LaVieja2
    fun getImageBreedsFromServer(mRazas:String) {
        val mCall = service.getImagesFromApi(mRazas)
        mCall.enqueue(object : Callback<DataImageBreedList>{
            override fun onResponse(call: Call<DataImageBreedList>, response: Response<DataImageBreedList>) {
                when(response.code()){
                    in 200..299 -> mLiveDataImageBreedList.postValue(response.body()?.message)
                    in 300..399 -> Log.d("ERROR 300 Images", response.errorBody().toString())
                }

            }

            override fun onFailure(call: Call<DataImageBreedList>, t: Throwable) {
                Log.e("Repository Images", t.message.toString())
            }

        })
    }

}