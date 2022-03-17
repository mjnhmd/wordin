package com.youdao.wordin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.youdao.wordin.model.Idiom
import com.youdao.wordin.model.IdiomDao
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    var word : Idiom? = null
    private val adapter = ResultAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDataBase()
    }

    private fun initDataBase(){
        GlobalScope.launch(Dispatchers.Main) {
            word = withContext(Dispatchers.IO) {
                IdiomDataManager.instance.database.getDao().getRandomIdiom()
            }
            Log.d("MJNTEST", "getword   " + word?.char1)
            initView()
        }
    }

    private fun initView(){
        Log.d("MJNTEST", "initView")
        if (word == null){
            adapter.setAnswer("国泰民安".toList())
        } else {
            adapter.setAnswer((word!!.char1+word!!.char2+ word!!.char3+ word!!.char4).toList())
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        tv_renew.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                word = withContext(Dispatchers.IO) {
                    IdiomDataManager.instance.database.getDao().getFirstIdiom()
                }
                Log.d("MJNTEST", "getword   " + word?.char1)
            }
        }
        et_input.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        tv_submit.setOnClickListener {
            val result = et_input.editableText.toList()
            adapter.setData(result)
            et_input.text = SpannableStringBuilder("")
        }
    }
}