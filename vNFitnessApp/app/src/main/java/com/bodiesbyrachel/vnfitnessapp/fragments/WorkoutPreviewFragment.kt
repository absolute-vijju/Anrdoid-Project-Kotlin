package com.bodiesbyrachel.vnfitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.models.Data
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_training.*
import kotlinx.android.synthetic.main.fragment_workout_preview.*
import kotlinx.android.synthetic.main.fragment_workout_preview.ivBack
import kotlinx.android.synthetic.main.fragment_workout_preview.tvWorkoutName
import kotlinx.android.synthetic.main.item_directions.view.*

/**
 * A simple [Fragment] subclass.
 */
class WorkoutPreviewFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val workoutPreviewFragment = WorkoutPreviewFragment()
            workoutPreviewFragment.arguments = bundle
            return workoutPreviewFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivBack.setOnClickListener(this)

        val data = Gson().fromJson<Data>(
            arguments!!.getString(Constants.WORKOUT_PREVIEW_DATA),
            Data::class.java
        )

        tvWorkoutName.text = data.exercise

        val directionList = data.directions!!.split("/n")

        for (directionIndex in directionList.indices) {
            val directionView =
                LayoutInflater.from(activity).inflate(R.layout.item_directions, llDirections, false)
            directionView.tvDirections.text =
                (directionIndex + 1).toString().plus(". ").plus(directionList[directionIndex])
            llDirections.addView(directionView)
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivBack -> {
                activity!!.onBackPressed()
            }
        }
    }
}