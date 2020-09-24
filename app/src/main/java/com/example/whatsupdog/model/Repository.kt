package com.example.whatsupdog.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.whatsupdog.model.retroFit.DataBreedList
import com.example.whatsupdog.model.retroFit.DataImageList
import com.example.whatsupdog.model.retroFit.RetroFitClient
import com.example.whatsupdog.model.room.BreedsDAO
import com.example.whatsupdog.model.room.DataBreedDBList
import com.example.whatsupdog.model.room.DataImagesDBList
import com.example.whatsupdog.model.room.ImagesDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository (private val mBreedsDAO: BreedsDAO, private val mImagesDAO: ImagesDAO){
    private val service = RetroFitClient.getRetrofitClient()

    val mLiveDataBreedList = mBreedsDAO.getAllBreedsFromDB()
    val mLiveDataImageBreedList = mImagesDAO.getAllImagesFromDB()

    val mDataBreedsDBList =  mutableListOf<DataBreedDBList>()
    val mDataImagesDBList = mutableListOf<DataImagesDBList>()

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
        mCall.enqueue(object : Callback<DataImageList>{
            override fun onResponse(call: Call<DataImageList>, response: Response<DataImageList>) {
                  when(response.code()){
                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let {
                           mImagesDAO.insertAllImages(transformationImages(mRazas,it.message))
                            Log.d(" Arroz Repo =","entro a la vieja2 correcto")
                            Log.d(" Arroz Repo =",transformationImages(mRazas,it.message).toString())
                        }

                    }

                    in 300..399 -> Log.d("ERROR 300 Images", response.errorBody().toString())
                }

            }

            override fun onFailure(call: Call<DataImageList>, t: Throwable) {
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

    fun transformationImages(mRaza:String,mStringList:List<String>):List<DataImagesDBList>{

        mStringList.map{
            mDataImagesDBList.add(DataImagesDBList(breed = mRaza, imageUrl =it, status = false))
        }
        return mDataImagesDBList
    }


}