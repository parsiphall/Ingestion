package com.example.parsiphal.ingestion.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class FiveIngModel(@PrimaryKey(autoGenerate = true) @ColumnInfo var  id: Long = 0,
                        @ColumnInfo var date: String = "-",
                        @ColumnInfo var weight: String = "-",
                        @ColumnInfo var breakfast: String = "-",
                        @ColumnInfo var snack1: String = "-",
                        @ColumnInfo var lunch: String = "-",
                        @ColumnInfo var snack2: String = "-",
                        @ColumnInfo var dinner: String = "-",
                        @ColumnInfo var water: String = "0")