package com.youdao.wordin.model

data class PinyinModel(
    val word: String,
    val sm: String?,
    val ym: String?,
    val yd: Int,
) {
    override fun toString(): String {
        return "sm = $sm, ym = $ym, yd = $yd"
    }
}