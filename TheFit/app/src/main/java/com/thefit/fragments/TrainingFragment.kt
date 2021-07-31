package com.thefit.fragments

import android.content.Intent
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
import com.thefit.activities.ProgramActivity
import com.thefit.adapters.TrainingAdapter
import com.thefit.models.TrainingResponse
import com.thefit.utils.GeneralFunctions
import com.thefit.utils.ItemClickListener
import kotlinx.android.synthetic.main.fragment_training.*

class TrainingFragment : Fragment(), ItemClickListener {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val trainingFragment = TrainingFragment()
            trainingFragment.arguments = bundle
            return trainingFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setAdapter()
    }

    private fun init() {
        rvTraining.layoutManager = LinearLayoutManager(activity!!)
    }

    private fun setAdapter() {
        val trainingResponse = Gson().fromJson(
            GeneralFunctions.loadJSONFromAsset(activity!!, "training_data.json"),
            TrainingResponse::class.java
        )
        val trainingAdapter = TrainingAdapter(activity!!, trainingResponse.trainingData!!, this)
        rvTraining.adapter = trainingAdapter
    }

    override fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?) {
        when (viewId) {
            R.id.ivMenu -> Toast.makeText(
                activity!!,
                activity!!.getString(R.string.menu_clicked),
                Toast.LENGTH_SHORT
            ).show()
            else -> {
                if (position == 2)
                    activity!!.startActivity(Intent(activity!!, ProgramActivity::class.java))
            }
        }
    }

}