package com.youdao.wordin

import androidx.room.Database
import androidx.room.RoomDatabase
import com.youdao.wordin.model.Idiom
import com.youdao.wordin.model.IdiomDao

@Database(entities = [Idiom::class], version = 1, exportSchema = false)
abstract class IdiomDatabase: RoomDatabase() {
    abstract fun getDao() : IdiomDao

}