package com.youdao.wordin.model

import androidx.room.*

@Dao
interface IdiomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(idiom: Idiom)

    @Delete
     fun delete(idiom: Idiom)

    @Query("SELECT * FROM idioms LIMIT 10")
     fun getAll(): List<Idiom>

    @Query("SELECT * FROM idioms ORDER BY RANDOM() LIMIT 1")
    fun getRandomIdiom(): Idiom

}