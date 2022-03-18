package com.youdao.wordin

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_result.view.*

class ResultAdapter: RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {
    var answer = emptyList<Char>().toMutableList()
    val data = emptyList<List<Char>>().toMutableList()
    @JvmName("setAnswer1")
    fun setAnswer(list: List<Char>?){
        list?.let {
            this.answer.clear()
            this.answer.addAll(list)
        }
    }
    fun setData(list: List<Char>){
        if (list.size > 4){
            val result = list.subList(0, 3)
        }
        data.add(list)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_result, parent, false)
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val item = data[position]
        holder.dealResult(item, answer)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ResultViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun dealResult(input: List<Char>, answer: List<Char>){
            for (i in input.indices){
                val char = input[i]
                when(i){
                    0 -> dealSingleChar(view.tv_0, char, answer, i)
                    1 -> dealSingleChar(view.tv_1, char, answer, i)
                    2 -> dealSingleChar(view.tv_2, char, answer, i)
                    3 -> dealSingleChar(view.tv_3, char, answer, i)
                }
            }
        }

        private fun dealSingleChar(text: TextView, char: Char, answer: List<Char>, index: Int){
            text.text = char.toString()
            if (char == answer[index]){
                text.setBackgroundColor(text.context.resources.getColor(R.color.right))
                text.setTextColor(text.context.resources.getColor(R.color.white))
            } else {
                text.setBackgroundColor(text.context.resources.getColor(R.color.gray))
                if (answer.contains(char)){
                    text.setTextColor(text.context.resources.getColor(R.color.word))
                } else {
                    text.setTextColor(Color.BLACK)
                }
            }
        }
    }
}