package com.youdao.wordin

import android.util.Pair
import com.youdao.wordin.model.Idiom

object IdiomUtil {
    fun getPinyinList(idiom: Idiom): List<Pair<String, String>>{
        val result = emptyList<Pair<String, String>>().toMutableList()
        val word = idiom.idiom.toList()
        for (i in word.indices){
            val item = when(i){
                0 -> Pair(word[i].toString(), idiom.sm1 + idiom.ym1)
                1 -> Pair(word[i].toString(), idiom.sm2 + idiom.ym2)
                2 -> Pair(word[i].toString(), idiom.sm3 + idiom.ym3)
                3 -> Pair(word[i].toString(), idiom.sm4 + idiom.ym4)
                else -> Pair(word[i].toString(), idiom.sm4 + idiom.ym4)
            }
            result.add(item)
        }
        return result
    }
}