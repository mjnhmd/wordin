package com.youdao.wordin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.youdao.wordin.model.Idiom
import com.youdao.wordin.model.IdiomDao
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    var word : Idiom? = null
    var tipCount = 0
    private val adapter = ResultAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDataBase()
//        initView()
    }

    private fun initDataBase(){
        GlobalScope.launch(Dispatchers.Main) {
            word = withContext(Dispatchers.IO) {
                IdiomDataManager.instance.database.getDao().getRandomIdiom()
            }
            Log.d("MJNTEST", "getword   " + word)
            initView()
        }
    }

    private fun initView(){
        Log.d("MJNTEST", "initView")
        if (word == null){
            GlobalScope.launch(Dispatchers.Main) {
                word = withContext(Dispatchers.IO) {
                    IdiomDataManager.instance.database.getDao().getIdiom("国泰民安")
                }
                adapter.setAnswer(word)
            }
        } else {
            adapter.setAnswer(word)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        tv_renew.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                word = withContext(Dispatchers.IO) {
                    IdiomDataManager.instance.database.getDao().getRandomIdiom()
                }
                tipCount = 0
                clearTip()
                adapter.setAnswer(word)
                adapter.clear()
                Log.d("MJNTEST", "getword   " + word?.toString())
            }
        }
        tv_tip.setOnClickListener {
            when(tipCount){
                0 -> {
                    tv_pinyin1.visibility = View.VISIBLE
                    tv_pinyin1.text = "第一个字拼音：" + word?.sm1 + word?.ym1
                }
                1-> {
                    tv_word1.visibility = View.VISIBLE
                    tv_word1.text = "第一个字是：" + word?.idiom?.toList()?.get(0).toString()
                }
                2 -> {
                    tv_pinyin2.visibility = View.VISIBLE
                    tv_pinyin2.text = "第二个字拼音：" + word?.sm2 + word?.ym2
                }
                3-> {
                    tv_word2.visibility = View.VISIBLE
                    tv_word2.text = "第二个字是：" + word?.idiom?.toList()?.get(1).toString()
                }
                4 -> {
                    tv_pinyin3.visibility = View.VISIBLE
                    tv_pinyin3.text = "第三个字拼音：" + word?.sm3 + word?.ym3
                }
                5-> {
                    tv_word3.visibility = View.VISIBLE
                    tv_word3.text = "第三个字是：" + word?.idiom?.toList()?.get(2).toString()
                }
                else -> {
                    Toast.makeText(this, "你干脆别玩了", Toast.LENGTH_SHORT).show()
                }
            }
            tipCount++
        }

        tv_submit.setOnClickListener {
            val result = et_input.editableText.toString()
            GlobalScope.launch(Dispatchers.Main) {
                val pinyin = PinyinUtil.getPinYin(result)
                val tone = PinyinUtil.generatePinyinModel(result)
                Log.d("MJNTEST", "getpinyin   " + pinyin)
                Log.d("MJNTEST", "getpinyin   " + tone)
                adapter.setData(tone)
            }
            et_input.text = SpannableStringBuilder("")
        }
    }

    private fun clearTip(){
        tv_pinyin1.visibility = View.GONE
        tv_pinyin2.visibility = View.GONE
        tv_pinyin3.visibility = View.GONE
        tv_word1.visibility = View.GONE
        tv_word2.visibility = View.GONE
        tv_word3.visibility = View.GONE
    }
}