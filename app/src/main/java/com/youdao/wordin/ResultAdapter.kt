package com.youdao.wordin

import android.graphics.Color
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.youdao.wordin.model.Idiom
import com.youdao.wordin.model.PinyinModel
import kotlinx.android.synthetic.main.adapter_result.view.*
import kotlinx.android.synthetic.main.item_single_word.view.*

class ResultAdapter: RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {
    var answer : Idiom? = null
    val answerList = emptyList<PinyinModel>().toMutableList()
    val data = emptyList<List<PinyinModel>>().toMutableList()
    @JvmName("setAnswer1")
    fun setAnswer(answer: Idiom?){
        if (answer != null){
            this.answer = answer
            answerList.clear()
            answerList.addAll(PinyinUtil.pinyinmodelFromIdiom(answer))
        }
    }
    fun clear(){
        data.clear()
        notifyDataSetChanged()
    }
    fun setData(input: List<PinyinModel>?){
        if (input == null){
            return
        }
        data.add(input)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_result, parent, false)
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val item = data[position]
        answer?.let{holder.dealResult(item, it)}
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ResultViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun dealResult(input: List<PinyinModel>, answer: Idiom){
            val pairlist = PinyinUtil.getPinyinList(input)
            view.ll_result.removeAllViews()
            for (i in input.indices){
                view.ll_result.addView(dealSingleChar(view.ll_result, input[i], answerList[i], pairlist[i]))
            }
            if (input.size < 4){
                val lastsize = 4 - input.size
                for (i in 0 until lastsize){
                    val itemView = LayoutInflater.from(view.context).inflate(R.layout.item_stab, view.ll_result, false)
                    view.ll_result.addView(itemView)
                }
            }
        }

        private fun dealSingleChar(view: LinearLayoutCompat, word: PinyinModel, answerModel: PinyinModel, pair: Pair<String, String>): View{
            val itemView = LayoutInflater.from(view.context).inflate(R.layout.item_single_word, view, false)
            itemView.run {
                tv_word.text = word.word
                tv_tone.text = when(word.yd){
                    1 -> "一声"
                    2 -> "二声"
                    3 -> "三声"
                    4 -> "四声"
                    5 -> "轻声"
                    else -> ""
                }
                tv_sm.text = word.sm
                tv_ym.text = word.ym
                if (word.word == answerModel.word){
                    //完全正确的情况
                    ll_bg.setBackgroundColor(context.resources.getColor(R.color.right))
                    tv_word.setTextColor(context.resources.getColor(R.color.white))
                    tv_sm.setTextColor(context.resources.getColor(R.color.white))
                    tv_ym.setTextColor(context.resources.getColor(R.color.white))
                    tv_tone.setTextColor(context.resources.getColor(R.color.white))
                } else {
                    tv_word.setTextColor(context.resources.getColor(R.color.black))
                    tv_sm.setTextColor(context.resources.getColor(R.color.black))
                    tv_ym.setTextColor(context.resources.getColor(R.color.black))
                    tv_tone.setTextColor(context.resources.getColor(R.color.black))
                    ll_bg.setBackgroundColor(context.resources.getColor(R.color.gray))
                    if (answer!!.idiom.contains(word.word)){
                        tv_word.setTextColor(context.resources.getColor(R.color.word))
                    } else {
                        tv_word.setTextColor(context.resources.getColor(R.color.black))
                    }
                    if (answerModel.sm == word.sm){
                        tv_sm.setTextColor(context.resources.getColor(R.color.right))
                    } else {
                        if (answer!!.sm1 == word.sm || answer!!.sm2 == word.sm || answer!!.sm3 == word.sm || answer!!.sm4 == word.sm){
                            tv_sm.setTextColor(context.resources.getColor(R.color.word))
                        } else {
                            tv_sm.setTextColor(context.resources.getColor(R.color.black))
                        }
                    }
                    if (answerModel.ym == word.ym){
                        tv_ym.setTextColor(context.resources.getColor(R.color.right))
                    } else {
                        if (answer!!.ym1 == word.ym || answer!!.ym2 == word.ym || answer!!.ym3 == word.ym || answer!!.ym4 == word.ym){
                            tv_ym.setTextColor(context.resources.getColor(R.color.word))
                        } else {
                            tv_ym.setTextColor(context.resources.getColor(R.color.black))
                        }
                    }
                    if (answerModel.yd == word.yd){
                        tv_tone.setTextColor(context.resources.getColor(R.color.word))
                    } else {
                        tv_tone.setTextColor(context.resources.getColor(R.color.black))
                    }
                }
            }
            return itemView
        }

    }
}