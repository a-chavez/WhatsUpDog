package com.example.whatsupdog.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImagesDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllImages(mImagesList: List<DataImagesDBList>)

    @Query("SELECT * FROM images_table")
    fun getAllImagesFromDB(): LiveData<List<DataImagesDBList>>

}