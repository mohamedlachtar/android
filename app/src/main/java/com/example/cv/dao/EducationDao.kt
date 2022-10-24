package com.example.cv.dao

import androidx.room.*
import com.example.cv.models.Company
import com.example.cv.models.Education

@Dao
interface EducationDao {

    @Insert
    fun insert(education: Education)

    @Update
    fun update(education: Education)

    @Delete
    fun delete(education: Education)

    @Query("SELECT * FROM education")
    fun getAll() : MutableList<Education>
}