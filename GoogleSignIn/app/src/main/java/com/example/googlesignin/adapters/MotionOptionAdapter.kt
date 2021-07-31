package com.example.googlesignin.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.googlesignin.R
import com.example.googlesignin.activities.QuestionActivity
import com.example.googlesignin.models.OptionData
import com.example.googlesignin.utils.ItemClickListener
import com.example.googlesignin.utils.OptionChangeListener
import kotlinx.android.synthetic.main.btn_continue.view.*
import kotlinx.android.synthetic.main.item_option.view.*
import kotlinx.coroutines.*

class MotionOptionAdapter(
    private val activity: Activity,
    private val optionData: ArrayList<OptionData>,
    private var selectedOption: Int,
    private val itemClickListener: ItemClickListener,
    private val currentQuestion: Int,
    private var optionChangeListener: OptionChangeListener,
    private var isContinueClicked: Boolean = false,
    private var isOptionClicked: Boolean = false
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var enterAnimationDelay: Long = 500
    private var job: Job? = null
    private var jobsList = arrayListOf<Job>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.item_option, parent, false)
        return OptionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return optionData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is OptionViewHolder) {
            holder.bindData(position)
            if (isContinueClicked) {
                holder.itemView.tvOption.setBackgroundResource(R.drawable.bg_option_selected)
                holder.itemView.tvOption.setTextColor(
                    ContextCompat.getColor(
                        activity,
                        R.color.white
                    )
                )
                val animation = AnimationUtils.loadAnimation(activity, R.anim.exit_from_left)
                animation.setInterpolator(activity, android.R.anim.linear_interpolator)
                holder.itemView.animation = animation
            } else if (selectedOption == -1) {
                holder.itemView.tvOption.visibility = View.INVISIBLE
                setEnterAnimation(holder.itemView, position)
            } else if (selectedOption != position) {
                setExitAnimation(holder.itemView, position)
            }

        }
    }

    private fun setExitAnimation(view: View, position: Int) {
        val animation = AnimationUtils.loadAnimation(activity, R.anim.left_to_right)
        animation.setInterpolator(activity, android.R.anim.linear_interpolator)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                view.tvOption.visibility = View.GONE

            }

            override fun onAnimationStart(p0: Animation?) {
                view.tvOption.visibility = View.VISIBLE
            }
        })
        view.tvOption.startAnimation(animation)
    }

    private fun setEnterAnimation(view: View, position: Int) {
        job = GlobalScope.launch {
            delay(enterAnimationDelay * position)
            withContext(Dispatchers.Main) {
                val animation = AnimationUtils.loadAnimation(activity, R.anim.right_to_left)
                animation.setInterpolator(activity, android.R.anim.linear_interpolator)
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(p0: Animation?) {
                        //
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        view.tvOption.visibility = View.VISIBLE
                    }

                    override fun onAnimationStart(p0: Animation?) {
                        view.tvOption.visibility = View.VISIBLE
                    }
                })
                view.startAnimation(animation)
            }
        }
        jobsList.add(job!!)
    }

    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData(position: Int) {

            /*if (position == optionData.size - 1)
                itemView.btnContinueOption.visibility = View.VISIBLE
            else
                itemView.btnContinueOption.visibility = View.GONE*/

            if (isOptionClicked)
                itemView.tvOption.setOnClickListener(null)
            else
                itemView.tvOption.setOnClickListener(this)

            if (selectedOption == adapterPosition) {
                itemView.tvOption.setBackgroundResource(R.drawable.bg_option_selected)
                itemView.tvOption.setTextColor(ContextCompat.getColor(activity, R.color.white))
            } else {
                itemView.tvOption.setBackgroundResource(R.drawable.bg_option_unselected)
                itemView.tvOption.setTextColor(ContextCompat.getColor(activity, R.color.black))
            }

            itemView.tvOption.text = optionData[position].option
        }

        private fun changeTint(adapterPosition: Int) {
            selectedOption = adapterPosition
            Log.d("mydata", selectedOption.toString())
            notifyDataSetChanged()
        }

        override fun onClick(p0: View?) {
            when (p0?.id) {
                R.id.tvOption -> {
                    isOptionClicked = true
                    optionChangeListener.onOptionChange(adapterPosition)
                    for (i in jobsList.indices) {
                        jobsList[i].cancel()
                    }
                    jobsList.clear()
                    changeTint(adapterPosition)
                }
            }
        }


    }
}