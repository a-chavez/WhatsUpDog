package com.example.whatsupdog.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "breed_table")
data class DataBreedDBList (@PrimaryKey val mBreed:String)