package com.example.whatsupdog.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreedsDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBreeds(mBreedList: List<DataBreedDBList>)

    @Query("SELECT * FROM breed_table")
    fun getAllBreedsFromDB(): LiveData<List<DataBreedDBList>>

}