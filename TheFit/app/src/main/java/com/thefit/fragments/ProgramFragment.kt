package com.thefit.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.thefit.R
import com.thefit.activities.ProgramActivity
import com.thefit.adapters.ProgramAdapter
import com.thefit.models.ProgramResponse
import com.thefit.utils.Constants
import com.thefit.utils.GeneralFunctions
import com.thefit.utils.ItemClickListener
import kotlinx.android.synthetic.main.fragment_program.*
import kotlinx.android.synthetic.main.item_week.view.*

class ProgramFragment : Fragment(), ItemClickListener {

    private var totalWeek: Int = 1
    private var currentWeek: Int = 0
    private var programAdapter: ProgramAdapter? = null
    private var workoutType: Constants.WorkoutType = Constants.WorkoutType.Gym
    private var currentDay: Int = 0

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val programFragment = ProgramFragment()
            programFragment.arguments = bundle
            return programFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_program, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        if ((activity as ProgramActivity).programResponse == null) {
            (activity as ProgramActivity).programResponse= Gson().fromJson(GeneralFunctions.loadJSONFromAsset(activity!!, "program_data.json"), ProgramResponse::class.java)
        }
        rvProgram.layoutManager = LinearLayoutManager(activity!!)

        if ((activity as ProgramActivity).programResponse!= null) {
            totalWeek =( activity as ProgramActivity).programResponse!!.weekData!!.size
            setAdapter()
        }
    }

    private fun setAdapter() {
        programAdapter = if (workoutType == Constants.WorkoutType.Gym)
            ProgramAdapter(activity!!,( activity as ProgramActivity).programResponse!!.weekData!![currentWeek].gymData!!.daysData!![currentDay].exerciseData!!, currentWeek, workoutType,currentDay,this)
        else
            ProgramAdapter(activity!!,( activity as ProgramActivity).programResponse!!.weekData!![currentWeek].homeData!!.daysData!![currentDay].exerciseData!!, currentWeek, workoutType,currentDay,this)
        rvProgram.adapter = programAdapter
    }

    private fun changeDay(viewId: Int?) {
        if (viewId != null) {
            when (viewId) {
                R.id.tvOne -> {
                    currentDay = 0
                    changeAdapterData()
                }
                R.id.tvTwo -> {
                    currentDay = 1
                    changeAdapterData()
                }
                R.id.tvThree -> {
                    currentDay = 2
                    changeAdapterData()
                }
                R.id.tvFour -> {
                    currentDay = 3
                    changeAdapterData()
                }
                R.id.tvFive -> {
                    currentDay = 4
                    changeAdapterData()
                }
                R.id.tvSix -> {
                    currentDay = 5
                    changeAdapterData()
                }
                R.id.tvSeven -> {
                    currentDay = 6
                    changeAdapterData()
                }
            }
        }
    }

    private fun changeType(viewId: Int?) {
        if (viewId != null) {
            when (viewId) {
                R.id.tvGym -> {
                    workoutType=Constants.WorkoutType.Gym
                    changeAdapterData()
                }
                R.id.tvHome -> {
                    workoutType=Constants.WorkoutType.Home
                    changeAdapterData()
                }
            }
        }
    }

    private fun changeWeek(viewHolder: RecyclerView.ViewHolder, viewId: Int?) {
        if (viewId != null) {
            when (viewId) {
                R.id.ivNext -> {
                    currentWeek++
                    if (currentWeek >= totalWeek - 1) {
                        if (currentWeek > totalWeek - 1)
                            currentWeek--
                        viewHolder.itemView.ivNext.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(activity!!, R.color.silver_chalice))
                    } else {
                        viewHolder.itemView.ivPrev.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(activity!!, R.color.black))
                    }
                    viewHolder.itemView.tvWeek.text = activity!!.getString(R.string.week).plus(" ").plus(currentWeek + 1)
                    changeAdapterData()
                }
                R.id.ivPrev -> {
                    currentWeek--
                    if (currentWeek <= 0) {
                        currentWeek = 0
                        viewHolder.itemView.ivPrev.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(activity!!, R.color.silver_chalice))
                    } else {
                        viewHolder.itemView.ivNext.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(activity!!, R.color.black))
                    }
                    viewHolder.itemView.tvWeek.text = activity!!.getString(R.string.week).plus(" ").plus(currentWeek + 1)
                    changeAdapterData()
                }
            }
        }
    }

    private fun changeAdapterData() {
        if (programAdapter != null) {
            if (workoutType == Constants.WorkoutType.Gym)
                programAdapter!!.setAdapter((activity as ProgramActivity).programResponse!!.weekData!![currentWeek].gymData!!.daysData!![currentDay].exerciseData!!, currentWeek,workoutType,currentDay)
            else
                programAdapter!!.setAdapter((activity as ProgramActivity).programResponse!!.weekData!![currentWeek].homeData!!.daysData!![currentDay].exerciseData!!, currentWeek,workoutType,currentDay)
        }
    }

    override fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?) {
        if (viewId != null) {
            when (viewId) {
                R.id.ivBack -> activity!!.onBackPressed()
                R.id.tvOne -> changeDay(R.id.tvOne)
                R.id.tvTwo -> changeDay(R.id.tvTwo)
                R.id.tvThree -> changeDay(R.id.tvThree)
                R.id.tvFour -> changeDay(R.id.tvFour)
                R.id.tvFive -> changeDay(R.id.tvFive)
                R.id.tvSix -> changeDay(R.id.tvSix)
                R.id.tvSeven -> changeDay(R.id.tvSeven)
                R.id.tvGym -> changeType(R.id.tvGym)
                R.id.tvHome -> changeType(R.id.tvHome)
                R.id.ivNext -> changeWeek(viewHolder, R.id.ivNext)
                R.id.ivPrev -> changeWeek(viewHolder, R.id.ivPrev)
                R.id.clWeekExercise -> {
                    val bundle = Bundle()
                    if (workoutType == Constants.WorkoutType.Gym)
                    bundle.putString(Constants.SELECTED_WORKOUT_DATA, Gson().toJson((activity as ProgramActivity).programResponse!!.weekData!![currentWeek].gymData!!.daysData!![currentDay].exerciseData!![position - 3]))
                    else
                    bundle.putString(Constants.SELECTED_WORKOUT_DATA, Gson().toJson((activity as ProgramActivity).programResponse!!.weekData!![currentWeek].homeData!!.daysData!![currentDay].exerciseData!![position - 3]))
                    (activity as ProgramActivity).replaceFragmentWithBackStack(SelectedWorkoutFragment.newInstance(bundle))
                }
            }
        }
    }

}