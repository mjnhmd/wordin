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
){
    override fun toString(): String {
        return "idiom = $idiom, pinyin = $pinyin, sm1 = $sm1, sm2 = $sm2, sm3 = $sm3, sm4 = $sm4, ym1 = $ym1, ym2 = $ym2, ym3 = $ym3, ym4 = $ym4, yd1 = $yd1, yd2 = $yd2, yd3 = $yd3, yd4 = $yd4"

    }

    fun getAllYm(): String{
        return ym1 + ym2 + ym3 + ym4
    }

    fun getAllSm(): String{
        return sm1 + sm2 + sm3 + sm4
    }
}
