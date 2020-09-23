package com.example.whatsupdog.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository (private val mBreedsDAO: BreedsDAO){
    private val service = RetroFitClient.getRetrofitClient()

    val mLiveDataBreedList = mBreedsDAO.getAllBreedsFromDB()
    val mLiveDataImageBreedList : MutableLiveData<List<String>> = MutableLiveData()
    val mDataBreedsDBList =  mutableListOf<DataBreedDBList>()

   //LaVieja1
    fun getBreedsFromServer() {
        val mCall = service.getBreedsFromApi()
        mCall.enqueue(object : Callback<DataBreedList>{
            override fun onResponse(call: Call<DataBreedList>, response: Response<DataBreedList>) {
                when(response.code()){

                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                       response.body()?.let {
                           mBreedsDAO.insertAllBreeds(transformation(it.message))
                       }
                    }
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

    fun transformation(mStringList:List<String>):List<DataBreedDBList>{

        mStringList.map{
            mDataBreedsDBList.add(DataBreedDBList(it))
        }
        return mDataBreedsDBList
    }

}