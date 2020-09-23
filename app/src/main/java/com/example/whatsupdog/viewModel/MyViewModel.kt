package com.example.whatsupdog.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.whatsupdog.model.BreedsDB
import com.example.whatsupdog.model.DataBreedDBList
import com.example.whatsupdog.model.Repository
import kotlinx.coroutines.launch

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