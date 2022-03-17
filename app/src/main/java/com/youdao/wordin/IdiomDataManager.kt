package com.youdao.wordin

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class IdiomDataManager {
    companion object {
        val instance: IdiomDataManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            IdiomDataManager() }
    }

    var database: IdiomDatabase =
        Room.databaseBuilder(WordInApplication.instance, IdiomDatabase::class.java,"idioms.db")
            .createFromAsset("idioms.db")
            .build()

}