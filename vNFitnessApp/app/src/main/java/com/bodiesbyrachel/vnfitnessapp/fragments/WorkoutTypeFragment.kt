package com.bodiesbyrachel.vnfitnessapp.fragments

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat

import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.activities.SelectedWorkoutActivity
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import kotlinx.android.synthetic.main.fragment_welcome.*
import kotlinx.android.synthetic.main.fragment_workout_type.*

/**
 * A simple [Fragment] subclass.
 */
class WorkoutTypeFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val workoutTypeFragment = WorkoutTypeFragment()
            workoutTypeFragment.arguments = bundle
            return workoutTypeFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener()
        setData()
    }

    private fun listener() {
        ivBack.setOnClickListener(this)
        tvTrainAtHome.setOnClickListener(this)
        tvTrainAtGym.setOnClickListener(this)
    }

    private fun setData() {

        val workoutTitleStr = "Full Body\n"
        val workoutStr = "Workout"
        val trainAtStr = "Train At "
        val homeStr = "Home"
        val gymStr = "Gym"

        val workoutSpannableString =
            SpannableString(workoutTitleStr.toUpperCase() + workoutStr.toUpperCase())
        val foregroundColorSpan =
            ForegroundColorSpan(ContextCompat.getColor(activity!!, R.color.medium_red_violet))
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
            R.id.ivBack -> activity!!.onBackPressed()
            R.id.tvTrainAtHome -> {
                val bundle = Bundle()
                bundle.putString(Constants.WORKOUT_TYPE, getString(R.string.home))
                (activity as SelectedWorkoutActivity).replaceFragmentFromActivity(
                    SelectedWorkoutFragment.newInstance(bundle)
                )
            }
            R.id.tvTrainAtGym -> {
                val bundle = Bundle()
                bundle.putString(Constants.WORKOUT_TYPE, getString(R.string.gym))
                (activity as SelectedWorkoutActivity).replaceFragmentFromActivity(
                    SelectedWorkoutFragment.newInstance(bundle)
                )
            }
        }
    }

}
