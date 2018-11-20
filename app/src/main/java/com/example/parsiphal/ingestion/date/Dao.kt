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

    @Query("SELECT * FROM FiveIngModel WHERE date=:Date")
    fun getCurrentIngestion(Date: String): FiveIngModel

//    @Query("UPDATE FiveIngModel WHERE date=:prefs.lastUseDate")
//    fun updateCurIng(fiveIngModel: FiveIngModel)

    @Update
    fun updateIngestion(fiveIngModel: FiveIngModel)

    @Delete
    fun deleteIngestion(fiveIngModel: FiveIngModel)
}