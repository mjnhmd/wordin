package com.youdao.wordin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "idiom")
data class Idiom(
    @PrimaryKey
    val id: Int,
    val char1:String,
    val char2: String,
    val char3: String,
    val char4: String,
    val example: String?,
    val mean: String?,
    val py1: String,
    val py2: String,
    val py3: String,
    val py4: String,
    val pytone1: String,
    val pytone2: String,
    val pytone3: String,
    val pytone4: String,
    val source: String?
)
