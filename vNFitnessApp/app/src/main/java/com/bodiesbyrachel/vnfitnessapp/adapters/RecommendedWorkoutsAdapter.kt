package com.bodiesbyrachel.vnfitnessapp.adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.activities.WorkoutTypeActivity
import com.bodiesbyrachel.vnfitnessapp.models.WorkoutData
import com.bodiesbyrachel.vnfitnessapp.models.WorkoutResponse
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import com.bodiesbyrachel.vnfitnessapp.utils.GeneralFunction
import com.bodiesbyrachel.vnfitnessapp.utils.ItemClickListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_recommended_workout.view.*
import kotlinx.android.synthetic.main.item_workout.view.*

class RecommendedWorkoutsAdapter(
    private val activity: Activity,
    private val recommendedWorkoutData: List<WorkoutData>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<RecommendedWorkoutsAdapter.RecommendedWorkoutsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendedWorkoutsViewHolder {
        val view =
            LayoutInflater.from(activity).inflate(R.layout.item_recommended_workout, parent, false)
        return RecommendedWorkoutsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: RecommendedWorkoutsViewHolder, position: Int) {
        holder.bindData(position)
    }

    inner class RecommendedWorkoutsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int) {

            //transitionName will be unique
            ViewCompat.setTransitionName(itemView.ivRecommendedWorkouts, recommendedWorkoutData[position].workoutName)

            itemView.tvRecommendedWorkoutsName.text = recommendedWorkoutData[position].workoutName

            itemView.ivRecommendedWorkouts.setOnClickListener {
                val selectedWorkoutData = Gson().toJson(recommendedWorkoutData[position])
                val intent = Intent(activity, WorkoutTypeActivity::class.java)
                intent.putExtra(Constants.WORKOUT_TYPE, Constants.RECOMMENDED_WORKOUT)
                intent.putExtra(Constants.SELECTED_WORKOUT_DATA, selectedWorkoutData)
                intent.putExtra(Constants.TRANSITION_NAME, ViewCompat.getTransitionName(itemView.ivRecommendedWorkouts))
                val options = ActivityOptions.makeSceneTransitionAnimation(activity, itemView.ivRecommendedWorkouts, ViewCompat.getTransitionName(itemView.ivRecommendedWorkouts))
                activity.startActivity(intent, options.toBundle())

            }

        }
    }

}