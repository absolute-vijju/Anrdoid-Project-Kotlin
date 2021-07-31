package com.bodiesbyrachel.vnfitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.adapters.ProgressAdapter
import com.bodiesbyrachel.vnfitnessapp.models.ProgressResponse
import com.bodiesbyrachel.vnfitnessapp.utils.GeneralFunction
import com.bodiesbyrachel.vnfitnessapp.utils.ItemClickListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_progress.*

/**
 * A simple [Fragment] subclass.
 */
class ProgressFragment : Fragment(), ItemClickListener {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val progressFragment = ProgressFragment()
            progressFragment.arguments = bundle
            return progressFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
    }

    private fun setAdapter() {

        val progressResponse = Gson().fromJson(
            GeneralFunction.loadJSONFromAsset(activity!!, "progress_data.json"),
            ProgressResponse::class.java
        )

        rvProgress.layoutManager = LinearLayoutManager(activity!!)
        val progressAdapter = ProgressAdapter(activity!!, progressResponse.progressData!!, this)
        rvProgress.adapter = progressAdapter

    }

    override fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?) {
        TODO("Not yet implemented")
    }

}
