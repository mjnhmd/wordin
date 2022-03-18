package com.youdao.wordin

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class IdiomDataManager {
    companion object {
        val instance: IdiomDataManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            IdiomDataManager() }
    }

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Empty implementation, because the schema isn't changing.
        }
    }
    var database: IdiomDatabase =
        Room.databaseBuilder(WordInApplication.instance, IdiomDatabase::class.java,"idioms.db")
            .createFromAsset("abcde.db")
            .addMigrations(MIGRATION_1_2)
            .build()

}