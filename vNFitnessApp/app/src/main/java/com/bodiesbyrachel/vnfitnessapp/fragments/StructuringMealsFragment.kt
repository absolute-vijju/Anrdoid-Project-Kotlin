package com.bodiesbyrachel.vnfitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.activities.DashboardActivity
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import kotlinx.android.synthetic.main.fragment_structuring_meals.*
import kotlinx.android.synthetic.main.header.*

/**
 * A simple [Fragment] subclass.
 */
class StructuringMealsFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val structuringMealsFragment = StructuringMealsFragment()
            structuringMealsFragment.arguments = bundle
            return structuringMealsFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_structuring_meals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        listeners()
    }

    private fun init() {
        ivBack.visibility = View.VISIBLE
        tvTitle.text = getString(R.string.structuring_your_meals).toUpperCase()
    }

    private fun listeners() {
        ivBack.setOnClickListener(this)
        tvWeightLoss.setOnClickListener(this)
        tvWeightGain.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val bundle = Bundle()
        when (v?.id) {
            R.id.ivBack -> (activity as DashboardActivity).onBackPressed()
            R.id.tvWeightLoss -> {
                bundle.putSerializable(Constants.WEIGHT_TYPE, Constants.WeightType.LOSS)
                (activity as DashboardActivity).replaceFragmentFromActivity(
                    WeightFragment.newInstance(
                        bundle
                    )
                )
            }
            R.id.tvWeightGain -> {
                bundle.putSerializable(Constants.WEIGHT_TYPE, Constants.WeightType.GAIN)
                (activity as DashboardActivity).replaceFragmentFromActivity(
                    WeightFragment.newInstance(
                        bundle
                    )
                )
            }
        }
    }

}
