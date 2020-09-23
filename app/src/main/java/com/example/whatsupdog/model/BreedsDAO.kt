package com.example.whatsupdog.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreedsDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBreeds(mBreedList: List<String>) //puede ser DataBreedsList ?

    @Query("SELECT * FROM breeds_table")
    fun getAllBreedsFromDB(): LiveData<List<String>>

}