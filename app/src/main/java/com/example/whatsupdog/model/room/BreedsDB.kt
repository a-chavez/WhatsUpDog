package com.example.whatsupdog.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATA_BASE_NAME="breeds_db"

@Database(entities = [DataBreedDBList::class],version = 1)

abstract class BreedsDB : RoomDatabase() {

    abstract fun getBreedsDAO(): BreedsDAO

    companion object {
        @Volatile
        private var INSTANCE: BreedsDB? = null

        fun getDataBase(context: Context): BreedsDB {
            //Para que no repita la instancia
            val tempInterface = INSTANCE
            if(tempInterface !=null) {
                return tempInterface
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context,
                    BreedsDB::class.java,
                    DATA_BASE_NAME
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}