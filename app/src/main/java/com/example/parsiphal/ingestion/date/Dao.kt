package com.example.parsiphal.ingestion.date

import android.arch.persistence.room.*
import android.arch.persistence.room.Dao
import com.example.parsiphal.ingestion.model.FiveIngModel

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addIngestion(fiveIngModel: FiveIngModel)

    @Query("SELECT * FROM FiveIngModel")
    fun getIngestions(): List<FiveIngModel>

    @Update
    fun updateIngestion(fiveIngModel: FiveIngModel)

    @Delete
    fun deleteIngestion(fiveIngModel: FiveIngModel)
}