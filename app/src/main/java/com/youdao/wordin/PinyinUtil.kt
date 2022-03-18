package com.youdao.wordin

import android.util.Pair
import com.youdao.wordin.model.Idiom
import com.youdao.wordin.model.PinyinModel
import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination
import java.lang.Exception


object PinyinUtil {
    fun getPinYin(inputString: String, withTone: Boolean = true): String {
        val format = HanyuPinyinOutputFormat()
        format.caseType = HanyuPinyinCaseType.LOWERCASE

        format.toneType = if (withTone){ HanyuPinyinToneType.WITH_TONE_MARK } else { HanyuPinyinToneType.WITHOUT_TONE}
        format.vCharType = HanyuPinyinVCharType.WITH_U_UNICODE
        val input = inputString.trim { it <= ' ' }.toCharArray()
        val output = StringBuffer("")
        try {
            for (i in input.indices) {
                if (Character.toString(input[i]).matches(Regex("[\u4E00-\u9FA5]+"))) {
                    val temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format)
                    output.append(temp[0])
                    output.append(" ")
                } else output.append(Character.toString(input[i]))
            }
        } catch (e: BadHanyuPinyinOutputFormatCombination) {
            e.printStackTrace()
        }
        return output.toString()
    }

    fun getPinYinTone(inputString: String): List<Int> {
        val format = HanyuPinyinOutputFormat()
        format.caseType = HanyuPinyinCaseType.LOWERCASE
        format.toneType = HanyuPinyinToneType.WITH_TONE_NUMBER
        format.vCharType = HanyuPinyinVCharType.WITH_U_UNICODE
        val input = inputString.trim { it <= ' ' }.toCharArray()
        val output = emptyList<Int>().toMutableList()
        try {
            for (i in input.indices) {
                if (Character.toString(input[i]).matches(Regex("[\u4E00-\u9FA5]+"))) {
                    val temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format)
                    val tone = temp[0].last().digitToInt()
                    output.add(tone)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return output
    }

    fun generatePinyinModel(inputString: String): List<PinyinModel>{
        val result = emptyList<PinyinModel>().toMutableList()
        val format = HanyuPinyinOutputFormat()
        format.caseType = HanyuPinyinCaseType.LOWERCASE
        format.toneType = HanyuPinyinToneType.WITH_TONE_NUMBER
        format.vCharType = HanyuPinyinVCharType.WITH_U_UNICODE
        val input = inputString.trim { it <= ' ' }.toCharArray()
        try {
            for (i in input.indices) {
                if (Character.toString(input[i]).matches(Regex("[\u4E00-\u9FA5]+"))) {
                    val temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format)
                    val pinyin = temp[0]
                    val toneChar = pinyin.last()
                    val tone = toneChar.digitToInt()
                    val sm = getSm(pinyin)
                    var ym = pinyin.removePrefix(sm)
                    ym = ym.removeSuffix(toneChar.toString())
                    val item = PinyinModel(input[i].toString(), sm, ym, tone)
                    result.add(item)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    fun getSm(input: String): String{
        if (input.isBlank()){
            return input
        }
        if (input.startsWith("a") || input.startsWith("e")){
            return ""
        }
        if (input.startsWith("zh")){
            return "zh"
        }
        if (input.startsWith("ch")){
            return "ch"
        }
        if (input.startsWith("sh")){
            return "sh"
        }
        return input.subSequence(0, 1).toString()
    }

    fun getPinyinList(list: List<PinyinModel>): List<Pair<String, String>>{
        val result = emptyList<Pair<String, String>>().toMutableList()
        for (i in list.indices){
            val word = list[i]
            val item = Pair(word.word, word.sm + word.ym)
            result.add(item)
        }
        return result
    }

    fun pinyinmodelFromIdiom(idiom: Idiom): List<PinyinModel>{
        val result = emptyList<PinyinModel>().toMutableList()
        val wordList = idiom.idiom.toList()
        for (i in wordList.indices){
            val word = wordList[i]
            when(i){
                0 -> {
                    var ym = idiom.ym1
                    val sm = idiom.sm1
                    if (ym == "uen"){
                        ym = "un"
                    }
                    val tone = idiom.yd1?.toInt() ?: 0
                    result.add(PinyinModel(word.toString(), sm, ym, tone))
                }
                1->{
                    var ym = idiom.ym2
                    val sm = idiom.sm2
                    if (ym == "uen"){
                        ym = "un"
                    }
                    val tone = idiom.yd2?.toInt() ?: 0
                    result.add(PinyinModel(word.toString(), sm, ym, tone))
                }
                2-> {
                    var ym = idiom.ym3
                    val sm = idiom.sm3
                    if (ym == "uen"){
                        ym = "un"
                    }
                    val tone = idiom.yd3?.toInt() ?: 0
                    result.add(PinyinModel(word.toString(), sm, ym, tone))
                }
                3-> {
                    var ym = idiom.ym4
                    val sm = idiom.sm4
                    if (ym == "uen"){
                        ym = "un"
                    }
                    val tone = idiom.yd4?.toInt() ?: 0
                    result.add(PinyinModel(word.toString(), sm, ym, tone))
                }
            }

        }
        return result
    }
}

