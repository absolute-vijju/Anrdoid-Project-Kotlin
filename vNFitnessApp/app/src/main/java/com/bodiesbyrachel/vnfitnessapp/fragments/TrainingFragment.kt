package com.bodiesbyrachel.vnfitnessapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.activities.SelectedWorkoutActivity
import com.bodiesbyrachel.vnfitnessapp.adapters.TrainingAdapter
import com.bodiesbyrachel.vnfitnessapp.models.Data
import com.bodiesbyrachel.vnfitnessapp.models.ExerciseData
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import com.bodiesbyrachel.vnfitnessapp.utils.ItemClickListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_training.*

/**
 * A simple [Fragment] subclass.
 */
class TrainingFragment : Fragment(), View.OnClickListener, ItemClickListener {

    var adapterData: List<Data>? = null

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val trainingFragment = TrainingFragment()
            trainingFragment.arguments = bundle
            return trainingFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivBack.setOnClickListener(this)

//        val type = object : TypeToken<List<Data>>() {}.type

        val exerciseData = Gson().fromJson(
            arguments!!.getString(Constants.SELECTED_WORKOUT_DATA),
            ExerciseData::class.java
        )

        adapterData = exerciseData.data

        tvWorkoutName.text = exerciseData.exerciseName

        rvTraining.layoutManager = LinearLayoutManager(activity)
        val trainingAdapter = TrainingAdapter(activity!!, adapterData!!, this)
        rvTraining.adapter = trainingAdapter

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivBack -> (activity as SelectedWorkoutActivity).onBackPressed()
        }
    }

    override fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?) {
        if (viewHolder is TrainingAdapter.StartViewHolder) {
            val bundle = Bundle()
            bundle.putString(Constants.WORKOUT_VIDEO_DATA, Gson().toJson(adapterData))
            (activity as SelectedWorkoutActivity).replaceFragmentFromActivity(
                WorkoutVideoFragment.newInstance(
                    bundle
                )
            )
        } else {
//            Log.d("mydata", Gson().toJson(adapterData!![position]))
            val bundle = Bundle()
            bundle.putString(Constants.WORKOUT_PREVIEW_DATA, Gson().toJson(adapterData!![position]))
            (activity as SelectedWorkoutActivity).replaceFragmentFromActivity(
                WorkoutPreviewFragment.newInstance(
                    bundle
                )
            )

        }
    }
}