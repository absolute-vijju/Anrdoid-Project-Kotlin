package com.bodiesbyrachel.vnfitnessapp.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.models.Data
import com.bodiesbyrachel.vnfitnessapp.utils.ItemClickListener
import kotlinx.android.synthetic.main.item_start.view.*
import kotlinx.android.synthetic.main.item_training.view.*
import kotlinx.android.synthetic.main.item_workout.view.*

class TrainingAdapter(
    private val activity: Activity,
    private val listOfData: List<Data>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        if (position == listOfData.size - 1)
            return R.layout.item_start
        if (position == listOfData.size)
            return R.layout.item_workout
        return R.layout.item_training
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        if (viewType == R.layout.item_start)
            return StartViewHolder(view)
        if (viewType == R.layout.item_workout)
            return HiitCardioViewHolder(view)
        return TrainingVieHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfData.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StartViewHolder)
            holder.bindData(position)
        else if (holder is HiitCardioViewHolder)
            holder.bindData(position)
        else
            (holder as TrainingVieHolder).bindData(position)
    }

    inner class TrainingVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int) {

            itemView.setOnClickListener {
                itemClickListener.onItemClick(
                    this,
                    position,
                    null
                )
            }

            itemView.tvExerciseName.text = listOfData[position].exercise

            val round = listOfData[position].round
            val reps = listOfData[position].reps

            itemView.tvRounds.text =
                listOfData[position].round.plus(" ").plus(activity.getString(R.string.rounds))
                    .plus(", ").plus(reps).plus(" ").plus(activity.getString(R.string.reps))
        }
    }

    inner class StartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int) {
            itemView.btnStart.setOnClickListener {
                itemClickListener.onItemClick(
                    this,
                    position,
                    null
                )
            }
        }
    }

    inner class HiitCardioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int) {
            itemView.tvWorkoutName.text = activity.getString(R.string.hiit_cardio)
        }
    }

}