package com.example.whatsupdog.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.whatsupdog.model.room.BreedsDB
import com.example.whatsupdog.model.room.DataBreedDBList
import com.example.whatsupdog.model.Repository
import com.example.whatsupdog.model.room.DataImagesDBList

class MyViewModel (application: Application): AndroidViewModel(application){

    private val mBreedsRepository : Repository
    val mAllBreeds : LiveData<List<DataBreedDBList>>
    val mAllImages : LiveData<List<DataImagesDBList>>



    init {
        val mBreedsDAO = BreedsDB.getDataBase(application).getBreedsDAO()
        val mImagesDAO = BreedsDB.getDataBase(application).getImagesDAO()
        mBreedsRepository = Repository(mBreedsDAO,mImagesDAO)
        mAllBreeds = mBreedsRepository.mLiveDataBreedList
        mAllImages = mBreedsRepository.mLiveDataImageBreedList
        mBreedsRepository.getBreedsFromServer()
        }


    fun getImages(mRaza : String) {
        mBreedsRepository.getImageBreedsFromServer(mRaza)
        Log.d("Arroz fragment viewModel =","Paso por getImages")
    }

}