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
    var ym1: String?,
    var ym2: String?,
    var ym3: String?,
    var ym4: String?,
    val yd1: String?,
    val yd2: String?,
    val yd3: String?,
    val yd4: String?
){
    override fun toString(): String {
        return "idiom = $idiom, pinyin = $pinyin, sm1 = $sm1, sm2 = $sm2, sm3 = $sm3, sm4 = $sm4, ym1 = $ym1, ym2 = $ym2, ym3 = $ym3, ym4 = $ym4, yd1 = $yd1, yd2 = $yd2, yd3 = $yd3, yd4 = $yd4"

    }

    init {
        if (ym1 == "uen"){
            ym1 = "un"
        }
        if (ym2 == "uen"){
            ym2 = "un"
        }
        if (ym3 == "uen"){
            ym3 = "un"
        }
        if (ym4 == "uen"){
            ym4 = "un"
        }
        if (yd1 == "y" && ym1 == "ian"){
            ym1 = "an"
        }
        if (yd2 == "y" && ym2 == "ian"){
            ym2 = "an"
        }
        if (yd3 == "y" && ym3 == "ian"){
            ym3 = "an"
        }
        if (yd4 == "y" && ym4 == "ian"){
            ym4 = "an"
        }
    }

    fun getAllYm(): String{
        return ym1 + ym2 + ym3 + ym4
    }

    fun getAllSm(): String{
        return sm1 + sm2 + sm3 + sm4
    }
}
