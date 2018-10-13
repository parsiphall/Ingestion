package com.example.parsiphal.ingestion.model

data class FiveIngModel(var type: Int = 1,
                        var date: String,
                        var weight: String,
                        var breakfast: String,
                        var snack1: String,
                        var lunch: String,
                        var snack2: String,
                        var dinner: String,
                        var water: String)