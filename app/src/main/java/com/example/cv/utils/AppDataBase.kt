package com.example.cv.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cv.dao.CompanyDao
import com.example.cv.dao.EducationDao
import com.example.cv.models.Company
import com.example.cv.models.Education

//TODO 8 "Change to a ROOMDataBase"
@Database(entities = [Company::class,Education::class], version = 1)

abstract class AppDataBase  : RoomDatabase() {

    //TODO 8.1 "Add the DAO"
    abstract fun companyDao(): CompanyDao
    abstract fun educationDao(): EducationDao


    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            if (instance == null) {
                synchronized(this) {
                    //TODO 8.2 "Build the DataBase"
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,"cv3",)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance!!
        }
    }
}


