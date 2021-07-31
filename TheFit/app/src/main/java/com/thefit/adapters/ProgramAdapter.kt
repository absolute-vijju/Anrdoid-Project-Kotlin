package com.thefit.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.thefit.R
import com.thefit.activities.ProgramActivity
import com.thefit.models.ExerciseData
import com.thefit.utils.Constants
import com.thefit.utils.ItemClickListener
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.header.view.tvTitle
import kotlinx.android.synthetic.main.item_week.view.*
import kotlinx.android.synthetic.main.item_week_exercise.view.*


class ProgramAdapter(
    private val activity: Activity,
    private var exerciseDataList: List<ExerciseData>,
    private var currentWeek: Int,
    private var workoutType:Constants.WorkoutType,
    private var currentDay: Int,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> R.layout.header
            1 -> R.layout.item_week
            2 -> R.layout.item_title
            else -> R.layout.item_week_exercise
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.header -> HeaderViewHolder(view)
            R.layout.item_week -> WeekViewHolder(view)
            R.layout.item_title -> TitleViewHolder(view)
            else -> WeekExerciseViewHolder(view)
        }

    }

    override fun getItemCount(): Int {
        return exerciseDataList.size + 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bindData()
            is WeekViewHolder -> holder.bindData()
            is TitleViewHolder -> holder.bindData()
            else -> (holder as WeekExerciseViewHolder).bindData(exerciseDataList[position - 3])
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData() {
            itemView.ivMenu.visibility = View.GONE
            itemView.ivBack.visibility = View.VISIBLE
            itemView.tvTitle.text = activity.getString(R.string.my_program).toUpperCase()
            itemView.ivBack.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.ivBack -> itemClickListener.onItemClick(this, adapterPosition, R.id.ivBack)
            }
        }
    }

    inner class WeekViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData() {

            checkTint()

            if (workoutType==Constants.WorkoutType.Gym){
                itemView.tvGym.setBackgroundResource(R.drawable.bg_selected_left)
                itemView.tvHome.setBackgroundResource(R.drawable.bg_unselected_right)
            }else{
                itemView.tvHome.setBackgroundResource(R.drawable.bg_selected_right)
                itemView.tvGym.setBackgroundResource(R.drawable.bg_unselected_left)
            }

            itemView.tvWeek.text = activity.getString(R.string.week).plus(" ").plus(currentWeek + 1)
            listeners()
        }

        private fun listeners() {
            itemView.tvOne.setOnClickListener(this)
            itemView.tvTwo.setOnClickListener(this)
            itemView.tvThree.setOnClickListener(this)
            itemView.tvFour.setOnClickListener(this)
            itemView.tvFive.setOnClickListener(this)
            itemView.tvSix.setOnClickListener(this)
            itemView.tvSeven.setOnClickListener(this)
            itemView.tvGym.setOnClickListener(this)
            itemView.tvHome.setOnClickListener(this)
            itemView.ivNext.setOnClickListener(this)
            itemView.ivPrev.setOnClickListener(this)
        }

        private fun checkTint() {
            when (currentDay) {
                0 -> {
                    itemView.tvOne.setBackgroundResource(R.drawable.bg_selected_day)
                    itemView.tvTwo.setBackgroundResource(0)
                    itemView.tvThree.setBackgroundResource(0)
                    itemView.tvFour.setBackgroundResource(0)
                    itemView.tvFive.setBackgroundResource(0)
                    itemView.tvSix.setBackgroundResource(0)
                    itemView.tvSeven.setBackgroundResource(0)
                }
                1 -> {
                    itemView.tvTwo.setBackgroundResource(R.drawable.bg_selected_day)
                    itemView.tvOne.setBackgroundResource(0)
                    itemView.tvThree.setBackgroundResource(0)
                    itemView.tvFour.setBackgroundResource(0)
                    itemView.tvFive.setBackgroundResource(0)
                    itemView.tvSix.setBackgroundResource(0)
                    itemView.tvSeven.setBackgroundResource(0)
                }
                2 -> {
                    itemView.tvThree.setBackgroundResource(R.drawable.bg_selected_day)
                    itemView.tvOne.setBackgroundResource(0)
                    itemView.tvTwo.setBackgroundResource(0)
                    itemView.tvFour.setBackgroundResource(0)
                    itemView.tvFive.setBackgroundResource(0)
                    itemView.tvSix.setBackgroundResource(0)
                    itemView.tvSeven.setBackgroundResource(0)
                }
                3 -> {
                    itemView.tvFour.setBackgroundResource(R.drawable.bg_selected_day)
                    itemView.tvOne.setBackgroundResource(0)
                    itemView.tvTwo.setBackgroundResource(0)
                    itemView.tvThree.setBackgroundResource(0)
                    itemView.tvFive.setBackgroundResource(0)
                    itemView.tvSix.setBackgroundResource(0)
                    itemView.tvSeven.setBackgroundResource(0)
                }
                4 -> {
                    itemView.tvFive.setBackgroundResource(R.drawable.bg_selected_day)
                    itemView.tvOne.setBackgroundResource(0)
                    itemView.tvTwo.setBackgroundResource(0)
                    itemView.tvThree.setBackgroundResource(0)
                    itemView.tvFour.setBackgroundResource(0)
                    itemView.tvSix.setBackgroundResource(0)
                    itemView.tvSeven.setBackgroundResource(0)
                }
                5 -> {
                    itemView.tvSix.setBackgroundResource(R.drawable.bg_selected_day)
                    itemView.tvOne.setBackgroundResource(0)
                    itemView.tvTwo.setBackgroundResource(0)
                    itemView.tvThree.setBackgroundResource(0)
                    itemView.tvFour.setBackgroundResource(0)
                    itemView.tvFive.setBackgroundResource(0)
                    itemView.tvSeven.setBackgroundResource(0)
                }
                6 -> {
                    itemView.tvSeven.setBackgroundResource(R.drawable.bg_selected_day)
                    itemView.tvOne.setBackgroundResource(0)
                    itemView.tvTwo.setBackgroundResource(0)
                    itemView.tvThree.setBackgroundResource(0)
                    itemView.tvFour.setBackgroundResource(0)
                    itemView.tvFive.setBackgroundResource(0)
                    itemView.tvSix.setBackgroundResource(0)
                }
            }
        }

        override fun onClick(p0: View?) {
            when (p0?.id) {
                R.id.tvOne -> itemClickListener.onItemClick(this, adapterPosition, R.id.tvOne)
                R.id.tvTwo -> itemClickListener.onItemClick(this, adapterPosition, R.id.tvTwo)
                R.id.tvThree -> itemClickListener.onItemClick(this, adapterPosition, R.id.tvThree)
                R.id.tvFour -> itemClickListener.onItemClick(this, adapterPosition, R.id.tvFour)
                R.id.tvFive -> itemClickListener.onItemClick(this, adapterPosition, R.id.tvFive)
                R.id.tvSix -> itemClickListener.onItemClick(this, adapterPosition, R.id.tvSix)
                R.id.tvSeven -> itemClickListener.onItemClick(this, adapterPosition, R.id.tvSeven)
                R.id.tvGym -> itemClickListener.onItemClick(this, adapterPosition, R.id.tvGym)
                R.id.tvHome -> itemClickListener.onItemClick(this, adapterPosition, R.id.tvHome)
                R.id.ivNext -> itemClickListener.onItemClick(this, adapterPosition, R.id.ivNext)
                R.id.ivPrev -> itemClickListener.onItemClick(this, adapterPosition, R.id.ivPrev)
            }
        }
    }

    inner class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData() {
            itemView.tvTitle.text =
                activity.getString(R.string.complete_stability_session).toUpperCase()
        }
    }

    inner class WeekExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData(exerciseData: ExerciseData?) {
            if (exerciseData!=null) {
                itemView.tvExerciseName.text = exerciseData.exerciseName
                itemView.cbExercise.isChecked = exerciseData.selected!!
            }

            itemView.cbExercise.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(compoundButton: CompoundButton?, isChecked: Boolean) {
                    if (compoundButton!!.isPressed) {
                        if (isChecked) {
                            if (workoutType == Constants.WorkoutType.Gym)
                                (activity as ProgramActivity).programResponse!!.weekData!![currentWeek].gymData!!.daysData!![currentDay].exerciseData!![adapterPosition - 3].selected = true
                            else
                                (activity as ProgramActivity).programResponse!!.weekData!![currentWeek].homeData!!.daysData!![currentDay].exerciseData!![adapterPosition - 3].selected = true
                        } else {
                            if (workoutType == Constants.WorkoutType.Gym)
                                (activity as ProgramActivity).programResponse!!.weekData!![currentWeek].gymData!!.daysData!![currentDay].exerciseData!![adapterPosition - 3].selected = false
                            else
                                (activity as ProgramActivity).programResponse!!.weekData!![currentWeek].homeData!!.daysData!![currentDay].exerciseData!![adapterPosition - 3].selected = false
                        }
                    }

                }
            })

            itemView.clWeekExercise.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            itemClickListener.onItemClick(this, adapterPosition, R.id.clWeekExercise)
        }
    }

    fun setAdapter(exerciseDataList: List<ExerciseData>, currentWeek: Int,workoutType: Constants.WorkoutType,currentDay: Int) {
        this.currentWeek=currentWeek
        this.exerciseDataList = exerciseDataList
        this.workoutType=workoutType
        this.currentDay=currentDay
        notifyDataSetChanged()
    }

}
