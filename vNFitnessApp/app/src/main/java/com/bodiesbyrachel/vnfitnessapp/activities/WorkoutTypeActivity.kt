package com.bodiesbyrachel.vnfitnessapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.fragments.SelectedWorkoutFragment
import com.bodiesbyrachel.vnfitnessapp.models.WorkoutData
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_workout_type.*
import kotlinx.android.synthetic.main.fragment_workout_type.*
import kotlinx.android.synthetic.main.fragment_workout_type.ivBack
import kotlinx.android.synthetic.main.fragment_workout_type.tvTrainAtGym
import kotlinx.android.synthetic.main.fragment_workout_type.tvTrainAtHome
import kotlinx.android.synthetic.main.fragment_workout_type.tvWorkoutName

class WorkoutTypeActivity : CustomActivity(), View.OnClickListener {

    var selectedWorkoutData: WorkoutData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_type)

        listener()
        setData()

    }

    private fun listener() {
        ivBack.setOnClickListener(this)
        tvTrainAtHome.setOnClickListener(this)
        tvTrainAtGym.setOnClickListener(this)
    }

    private fun setData() {

        val transitionName = intent.getStringExtra(Constants.TRANSITION_NAME)

        ivWorkout.transitionName = transitionName
        Log.d("mydata", transitionName!!)

        val workoutType = intent.getStringExtra(Constants.WORKOUT_TYPE)
        if (workoutType != null)
            ivWorkout.setImageResource(R.drawable.recommended_workout_placeholder)
        else
            ivWorkout.setImageResource(R.drawable.workout_placeholder)

        selectedWorkoutData = Gson().fromJson<WorkoutData>(
            intent.getStringExtra(Constants.SELECTED_WORKOUT_DATA),
            WorkoutData::class.java
        )

        val workoutTitleStr = "Full Body\n"
        val workoutStr = "Workout"
        val trainAtStr = "Train At "
        val homeStr = "Home"
        val gymStr = "Gym"

        val workoutSpannableString =
            SpannableString(workoutTitleStr.toUpperCase() + workoutStr.toUpperCase())
        val foregroundColorSpan =
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.medium_red_violet))
        workoutSpannableString.setSpan(
            foregroundColorSpan,
            workoutTitleStr.length,
            workoutSpannableString.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvWorkoutName.text = workoutSpannableString

        val boldStyleSpan = StyleSpan(R.style.Bold)
        val trainAtHomeSpannableString =
            SpannableString(trainAtStr.toUpperCase() + homeStr.toUpperCase())
        trainAtHomeSpannableString.setSpan(
            boldStyleSpan,
            trainAtStr.length,
            trainAtHomeSpannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvTrainAtHome.text = trainAtHomeSpannableString

        val trainAtGymSpannableString =
            SpannableString(trainAtStr.toUpperCase() + gymStr.toUpperCase())
        trainAtGymSpannableString.setSpan(
            boldStyleSpan,
            trainAtStr.length,
            trainAtGymSpannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvTrainAtGym.text = trainAtGymSpannableString

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivBack -> onBackPressed()
            R.id.tvTrainAtHome -> {
                val intent = Intent(this, SelectedWorkoutActivity::class.java)
                intent.putExtra(Constants.WORKOUT_TYPE, getString(R.string.home))
                intent.putExtra(Constants.SELECTED_WORKOUT_DATA, Gson().toJson(selectedWorkoutData))
                startActivity(intent)
            }
            R.id.tvTrainAtGym -> {
                val intent = Intent(this, SelectedWorkoutActivity::class.java)
                intent.putExtra(Constants.WORKOUT_TYPE, getString(R.string.gym))
                intent.putExtra(Constants.SELECTED_WORKOUT_DATA, Gson().toJson(selectedWorkoutData))
                startActivity(intent)
            }
        }
    }

}