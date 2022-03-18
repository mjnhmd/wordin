package com.youdao.wordin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "idioms")
data class Idiom(
    @PrimaryKey
    val idiom: String,
    val pinyin:String?,
    val sm1: String?,
    val sm2: String?,
    val sm3: String?,
    val sm4: String?,
    val ym1: String?,
    val ym2: String?,
    val ym3: String?,
    val ym4: String?,
    val yd1: String?,
    val yd2: String?,
    val yd3: String?,
    val yd4: String?
)
