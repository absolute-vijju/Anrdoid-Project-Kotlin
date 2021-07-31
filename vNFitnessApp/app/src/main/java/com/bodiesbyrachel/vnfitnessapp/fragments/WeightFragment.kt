package com.bodiesbyrachel.vnfitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.activities.DashboardActivity
import com.bodiesbyrachel.vnfitnessapp.adapters.WeightAdapter
import com.bodiesbyrachel.vnfitnessapp.models.MealSummary
import com.bodiesbyrachel.vnfitnessapp.models.WeightResponse
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import com.bodiesbyrachel.vnfitnessapp.utils.ItemClickListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_weight.*
import java.io.IOException
import java.io.InputStream
import java.io.Serializable
import java.nio.charset.Charset

/**
 * A simple [Fragment] subclass.
 */
class WeightFragment : Fragment(), ItemClickListener {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val weightFragment = WeightFragment()
            weightFragment.arguments = bundle
            return weightFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weight, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weightType = arguments!!.getSerializable(Constants.WEIGHT_TYPE)

        val weightResponse =
            Gson().fromJson(loadJSONFromAsset(weightType), WeightResponse::class.java)

        setAdapter(weightType!!, weightResponse.mealSummary!!)

    }

    private fun setAdapter(weightType: Serializable, weightData: List<MealSummary>) {
        rvWeight.layoutManager = LinearLayoutManager(activity!!)
        val weightAdapter = WeightAdapter(activity!!, weightType, weightData, this)
        rvWeight.adapter = weightAdapter
    }

    private fun loadJSONFromAsset(weightType: Serializable?): String? {
        var json: String? = null
        var inputStream: InputStream? = null
        try {
            if (weightType == Constants.WeightType.GAIN)
                inputStream = activity!!.assets.open("weight_gain.json")
            else
                inputStream = activity!!.assets.open("weight_loss.json")

            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    override fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?) {
        when (viewId) {
            R.id.ivBack -> (activity as DashboardActivity).onBackPressed()
        }
    }

}
