package com.example.parsiphal.ingestion.date

import android.arch.persistence.room.*
import com.example.parsiphal.ingestion.model.FiveIngModel

@Database(entities = [FiveIngModel::class], version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun getDao(): Dao
}