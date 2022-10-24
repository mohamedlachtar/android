package com.example.cv.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "companies")
class Company(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image:String,
    val name: String,
    val address: String,
    val startDate: String,
    val endDate : String
    )

