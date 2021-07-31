package com.example.recyclerviewmotionlayout

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class OptionAdapter(
    private val activity: Activity,
    private val optionData: ArrayList<String>,
    private var selectedOption: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        if (position < optionData.size)
            return R.layout.item_option
        return R.layout.btn_continue
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        if (viewType == R.layout.btn_continue)
            return ButtonViewHolder(view)
        return OptionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return optionData.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ButtonViewHolder)
            holder.bindData(position)
        if (holder is OptionViewHolder)
            holder.bindData(position)
    }

    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val tvOption = itemView.findViewById<TextView>(R.id.tvOption)

        fun bindData(position: Int) {

            tvOption.setOnClickListener(this)

            if (selectedOption == adapterPosition) {
                tvOption.setBackgroundResource(R.drawable.bg_option_selected)
                tvOption.setTextColor(ContextCompat.getColor(activity, R.color.white))
            } else {
                tvOption.setBackgroundResource(R.drawable.bg_option_unselected)
                tvOption.setTextColor(ContextCompat.getColor(activity, R.color.black))
            }

            tvOption.text = optionData[position]
        }

        private fun changeTint(adapterPosition: Int) {
            selectedOption = adapterPosition
            Log.d("mydata", selectedOption.toString())
            notifyDataSetChanged()
        }

        override fun onClick(p0: View?) {
            when (p0?.id) {
                R.id.tvOption -> {
                    changeTint(adapterPosition)
                }
            }
        }


    }

    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val btnContinue = itemView.findViewById<Button>(R.id.btnContinue)

        fun bindData(position: Int) {
            btnContinue.setOnClickListener(this)

            btnContinue.text = "Submit"

        }

        override fun onClick(p0: View?) {
            when (p0?.id) {
                R.id.btnContinue -> {

                }
            }
        }
    }
}