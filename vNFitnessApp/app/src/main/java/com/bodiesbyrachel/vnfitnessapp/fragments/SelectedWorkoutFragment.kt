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
import com.bodiesbyrachel.vnfitnessapp.adapters.SelectedWorkoutAdapter
import com.bodiesbyrachel.vnfitnessapp.models.ExerciseData
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import com.bodiesbyrachel.vnfitnessapp.utils.ItemClickListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_selected_workout.*

/**
 * A simple [Fragment] subclass.
 */
class SelectedWorkoutFragment : Fragment(), View.OnClickListener, ItemClickListener {

    private var workoutType: String? = null
    private val mainList = arrayListOf<Any>()

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val selectedWorkoutFragment = SelectedWorkoutFragment()
            selectedWorkoutFragment.arguments = bundle
            return selectedWorkoutFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selected_workout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivBack.setOnClickListener(this)

        workoutType = arguments!!.getString(Constants.WORKOUT_TYPE)

        tvTrainAt.text = getString(R.string.train_at).plus(" ").plus(workoutType)

        val workoutData = (activity as SelectedWorkoutActivity).selectedWorkoutData!!

        mainList.clear()

        for (daysDataIndex in workoutData.daysData!!.indices) {
            mainList.add(workoutData.daysData[daysDataIndex].day!!)

            if (workoutData.daysData[daysDataIndex].exerciseData!!.isNotEmpty()) {
                val exerciseData = workoutData.daysData[daysDataIndex].exerciseData
                for (exerciseDataIndex in exerciseData!!.indices) {
                    mainList.add(exerciseData[exerciseDataIndex])
                }

            }
        }

        rvSelectedWorkout.layoutManager = LinearLayoutManager(activity)
        val selectedWorkoutAdapter = SelectedWorkoutAdapter(activity!!, mainList, this)
        rvSelectedWorkout.adapter = selectedWorkoutAdapter

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivBack -> activity!!.onBackPressed()
        }
    }

    override fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?) {
        if (viewHolder is SelectedWorkoutAdapter.SelectedWorkoutViewHolder && mainList[position] !is String) {
            val bundle = Bundle()
            bundle.putString(Constants.SELECTED_WORKOUT_DATA, Gson().toJson((mainList[position] as ExerciseData)))
            (activity as SelectedWorkoutActivity).replaceFragmentFromActivity(TrainingFragment.newInstance(bundle))
        }
    }


}
