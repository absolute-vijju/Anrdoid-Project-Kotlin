package com.bodiesbyrachel.vnfitnessapp.adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.activities.WorkoutTypeActivity
import com.bodiesbyrachel.vnfitnessapp.models.WorkoutData
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import com.bodiesbyrachel.vnfitnessapp.utils.ItemClickListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_recommended.view.*
import kotlinx.android.synthetic.main.item_recommended_workout.*
import kotlinx.android.synthetic.main.item_recommended_workout.view.*
import kotlinx.android.synthetic.main.item_user_profile.view.*
import kotlinx.android.synthetic.main.item_workout.*
import kotlinx.android.synthetic.main.item_workout.view.*

class WorkoutsAdapter(
    private val activity: Activity,
    private val listOfWorkout: List<WorkoutData>,
    private val recommendedWorkoutData: List<WorkoutData>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> R.layout.item_user_profile
            1 -> R.layout.item_recommended
            else -> R.layout.item_workout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_user_profile -> UserViewHolder(view)
            R.layout.item_recommended -> RecommendedWorkoutViewHolder(view)
            else -> WorkoutViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return listOfWorkout.size + 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserViewHolder)
            holder.bindData(position)
        else if (holder is RecommendedWorkoutViewHolder)
            holder.bindData()
        else (holder as WorkoutViewHolder).bindData(position - 2)
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int) {
            itemView.ivProfilePic.setOnClickListener {
                itemClickListener.onItemClick(this, position, R.id.ivProfilePic)
            }
        }
    }

    inner class RecommendedWorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData() {
            itemView.rvRecommendedWorkouts.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            val recommendedWorkoutsAdapter =
                RecommendedWorkoutsAdapter(activity, recommendedWorkoutData, itemClickListener)
            itemView.rvRecommendedWorkouts.adapter = recommendedWorkoutsAdapter
        }
    }

    inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int) {

            //transitionName will be unique
            ViewCompat.setTransitionName(itemView.ivWorkout, listOfWorkout[position].workoutName)

            itemView.tvWorkoutName.text = listOfWorkout[position].workoutName

            itemView.ivWorkout.setOnClickListener {
                val selectedWorkoutData = Gson().toJson(listOfWorkout[position])
                val intent = Intent(activity, WorkoutTypeActivity::class.java)
                intent.putExtra(Constants.SELECTED_WORKOUT_DATA, selectedWorkoutData)
                intent.putExtra(Constants.TRANSITION_NAME, ViewCompat.getTransitionName(itemView.ivWorkout))
                val options = ActivityOptions.makeSceneTransitionAnimation(activity, itemView.ivWorkout, ViewCompat.getTransitionName(itemView.ivWorkout))
                activity.startActivity(intent, options.toBundle())
            }

        }
    }

}