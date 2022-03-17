package com.youdao.wordin.model

import androidx.room.*

@Dao
interface IdiomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(idiom: Idiom)

    @Delete
     fun delete(idiom: Idiom)

    @Query("SELECT * FROM idiom WHERE idiom.char1 = :char1 AND idiom.char2 = :char2 AND idiom.char3 = :char3 AND idiom.char4 = :char4")
     fun getIdiom(char1: String, char2: String, char3: String, char4: String, ): List<Idiom>

    @Query("SELECT * FROM idiom LIMIT 10")
     fun getAll(): List<Idiom>

    @Query("SELECT * FROM idiom ORDER BY RANDOM() LIMIT 1")
    fun getRandomIdiom(): Idiom

    @Query("SELECT * FROM idiom where id = 1")
    fun getFirstIdiom(): Idiom
}