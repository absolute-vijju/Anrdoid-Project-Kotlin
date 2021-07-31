package com.thefit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.thefit.R
import com.thefit.adapters.NutritionAdapter
import com.thefit.models.NutritionResponse
import com.thefit.utils.GeneralFunctions
import com.thefit.utils.ItemClickListener
import kotlinx.android.synthetic.main.fragment_nutrition.*

class NutritionFragment : Fragment(), ItemClickListener {

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
        return inflater.inflate(R.layout.fragment_nutrition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setAdapter()
    }

    private fun init() {
        rvNutrition.layoutManager = LinearLayoutManager(activity!!)
    }

    private fun setAdapter() {
        val nutritionResponse = Gson().fromJson(GeneralFunctions.loadJSONFromAsset(activity!!, "nutrition_data.json"), NutritionResponse::class.java)
        val nutritionAdapter = NutritionAdapter(activity!!, nutritionResponse.nutritionData!!, this)
        rvNutrition.adapter = nutritionAdapter
    }

    override fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?) {
        when (viewId) {
            R.id.ivMenu -> Toast.makeText(activity!!, activity!!.getString(R.string.menu_clicked), Toast.LENGTH_SHORT).show()
        }
    }

}