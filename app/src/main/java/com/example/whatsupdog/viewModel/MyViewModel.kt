package com.example.whatsupdog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whatsupdog.model.Repository

class MyViewModel: ViewModel(){

    private val mBreedsRepository = Repository()
    private val mImageRepository = Repository()


    init {
        mBreedsRepository.getBreedsFromServer()
        mImageRepository.getImageBreedsFromServer(mRazas = "Boxer")
    }

    fun exposeLiveDataBreedList() : MutableLiveData<List<String>> {

        return mBreedsRepository.mLiveDataBreedList

    }

    fun exposeLiveDataImageBreedList() : LiveData<List<String>>{
        return mImageRepository.mLiveDataImageBreedList
    }

}