package com.bodiesbyrachel.vnfitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.activities.DashboardActivity
import kotlinx.android.synthetic.main.fragment_nutrition.*
import kotlinx.android.synthetic.main.header.*

/**
 * A simple [Fragment] subclass.
 */
class NutritionFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val nutritionFragment = NutritionFragment()
            nutritionFragment.arguments = bundle
            return nutritionFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nutrition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        listeners()

    }

    private fun init() {
        tvTitle.text = getString(R.string.nutrition).toUpperCase()
    }

    private fun listeners() {
        tvStructuringYourMeals.setOnClickListener(this)
        tvGrocerryList.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvStructuringYourMeals -> (activity as DashboardActivity).replaceFragmentFromActivity(
                StructuringMealsFragment.newInstance(null)
            )
            R.id.tvGrocerryList -> (activity as DashboardActivity).replaceFragmentFromActivity(
                GroceryFragment.newInstance(null)
            )
        }
    }


}
