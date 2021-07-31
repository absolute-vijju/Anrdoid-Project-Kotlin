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
import com.thefit.adapters.WeightHistoryAdapter
import com.thefit.models.WeightHistoryResponse
import com.thefit.utils.GeneralFunctions
import com.thefit.utils.ItemClickListener
import kotlinx.android.synthetic.main.fragment_weight_history.*

class WeightHistoryFragment : Fragment(), ItemClickListener {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val weightHistoryFragment = WeightHistoryFragment()
            weightHistoryFragment.arguments = bundle
            return weightHistoryFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weight_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setAdapter()
    }

    private fun init() {
        rvWeightHistory.layoutManager = LinearLayoutManager(activity!!)
    }

    private fun setAdapter() {
        val weightHistoryResponse = Gson().fromJson(
            GeneralFunctions.loadJSONFromAsset(activity!!, "weight_history.json"),
            WeightHistoryResponse::class.java
        )
        rvWeightHistory.adapter =
            WeightHistoryAdapter(activity!!, weightHistoryResponse.weightHistoryData!!, this)
    }

    override fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?) {
        when (viewId) {
            R.id.ivMenu -> Toast.makeText(
                activity!!,
                activity!!.getString(R.string.menu_clicked),
                Toast.LENGTH_SHORT
            ).show()
            R.id.ivBack -> {
                if (activity!!.supportFragmentManager.backStackEntryCount > 0)
                    activity!!.supportFragmentManager.popBackStack()
                else
                    activity!!.finish()
            }
        }
    }


}