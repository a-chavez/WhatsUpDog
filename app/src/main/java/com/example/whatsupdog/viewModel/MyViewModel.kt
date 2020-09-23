package com.example.whatsupdog.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.whatsupdog.model.room.BreedsDB
import com.example.whatsupdog.model.room.DataBreedDBList
import com.example.whatsupdog.model.Repository

class MyViewModel (application: Application): AndroidViewModel(application){

    private val mBreedsRepository : Repository
    val mAllBreeds : LiveData<List<DataBreedDBList>>


    init {
        val mBreedsDAO = BreedsDB.getDataBase(application).getBreedsDAO()
        mBreedsRepository = Repository(mBreedsDAO)
        mAllBreeds = mBreedsRepository.mLiveDataBreedList
        mBreedsRepository.getBreedsFromServer()
    }


}