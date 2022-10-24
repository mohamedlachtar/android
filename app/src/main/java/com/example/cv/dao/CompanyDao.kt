package com.example.cv.dao

import androidx.room.*
import com.example.cv.models.Company


@Dao
interface CompanyDao {


        @Insert
        fun insert(company: Company)

        @Update
        fun update(company: Company)

        @Delete
        fun delete(company: Company)

        @Query("SELECT * FROM companies")
        fun getAll() : MutableList<Company>

}