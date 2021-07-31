package com.bodiesbyrachel.vnfitnessapp.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat

import com.bodiesbyrachel.vnfitnessapp.R
import kotlinx.android.synthetic.main.fragment_step_three.*

/**
 * A simple [Fragment] subclass.
 */
class StepThreeFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val stepThreeFragment = StepThreeFragment()
            stepThreeFragment.arguments = bundle
            return stepThreeFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_step_three, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener()

    }

    private fun listener() {
        llLooseBodyFat.setOnClickListener(this)
        llGainMuscle.setOnClickListener(this)
        llMaintain.setOnClickListener(this)
        btnNext.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.llLooseBodyFat -> {
                llLooseBodyFat.setBackgroundResource(R.drawable.bg_selected_goal)
                tvLooseBodyFat.setTextColor(Color.WHITE)
                tvLooseBodyFatDesc.setTextColor(Color.WHITE)

                llGainMuscle.setBackgroundResource(R.drawable.border_pink)
                tvGainMuscle.setTextColor(ContextCompat.getColor(activity!!,R.color.step_heading_desc))
                tvGainMuscleDesc.setTextColor(ContextCompat.getColor(activity!!,R.color.step_heading_desc))

                llMaintain.setBackgroundResource(R.drawable.border_pink)
                tvMaintain.setTextColor(ContextCompat.getColor(activity!!,R.color.step_heading_desc))
                tvMaintainDesc.setTextColor(ContextCompat.getColor(activity!!,R.color.step_heading_desc))
            }
            R.id.llGainMuscle -> {
                llGainMuscle.setBackgroundResource(R.drawable.bg_selected_goal)
                tvGainMuscle.setTextColor(Color.WHITE)
                tvGainMuscleDesc.setTextColor(Color.WHITE)

                llLooseBodyFat.setBackgroundResource(R.drawable.border_pink)
                tvLooseBodyFat.setTextColor(ContextCompat.getColor(activity!!,R.color.step_heading_desc))
                tvLooseBodyFatDesc.setTextColor(ContextCompat.getColor(activity!!,R.color.step_heading_desc))

                llMaintain.setBackgroundResource(R.drawable.border_pink)
                tvMaintain.setTextColor(ContextCompat.getColor(activity!!,R.color.step_heading_desc))
                tvMaintainDesc.setTextColor(ContextCompat.getColor(activity!!,R.color.step_heading_desc))
            }
            R.id.llMaintain -> {
                llMaintain.setBackgroundResource(R.drawable.bg_selected_goal)
                tvMaintain.setTextColor(Color.WHITE)
                tvMaintainDesc.setTextColor(Color.WHITE)

                llLooseBodyFat.setBackgroundResource(R.drawable.border_pink)
                tvLooseBodyFat.setTextColor(ContextCompat.getColor(activity!!,R.color.step_heading_desc))
                tvLooseBodyFatDesc.setTextColor(ContextCompat.getColor(activity!!,R.color.step_heading_desc))

                llGainMuscle.setBackgroundResource(R.drawable.border_pink)
                tvGainMuscle.setTextColor(ContextCompat.getColor(activity!!,R.color.step_heading_desc))
                tvGainMuscleDesc.setTextColor(ContextCompat.getColor(activity!!,R.color.step_heading_desc))
            }
            R.id.btnNext-> activity!!.finish()
        }
    }

}
