package com.example.googlesignin.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.googlesignin.R
import com.example.googlesignin.models.OptionData
import com.example.googlesignin.models.QuestionData
import com.example.googlesignin.utils.ItemClickListener
import com.example.googlesignin.utils.OptionChangeListener
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionAdapter(
    private val activity: Activity,
    private val questionData: ArrayList<QuestionData>,
    private val currentQuestion: Int,
    private val itemClickListener: ItemClickListener,
    private val optionChangeListener: OptionChangeListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> R.layout.item_question_header
            else -> R.layout.item_question
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_question_header -> HeaderViewHolder(view)
            else -> QuestionViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bindData()
            is QuestionViewHolder -> holder.bindData(questionData[currentQuestion])
        }

    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData() {}
    }

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(question: QuestionData?) {
            if (question != null) {
                itemView.tvQuestion.animation =
                    AnimationUtils.loadAnimation(activity, R.anim.fade_in)
                itemView.tvQuestion.text = question.question
                if (question.optionData != null)
                    setAdapter(question.optionData)
            }
        }

        private fun setAdapter(optionData: ArrayList<OptionData>) {
            itemView.rvOptions.layoutManager = LinearLayoutManager(activity)
            val optionAdapter =
                MotionOptionAdapter(
                    activity,
                    optionData,
                    -1,
                    itemClickListener,
                    currentQuestion,
                    optionChangeListener
                )
            itemView.rvOptions.adapter = optionAdapter
        }

    }
}