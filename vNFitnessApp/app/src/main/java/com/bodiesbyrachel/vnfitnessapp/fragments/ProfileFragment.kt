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
import com.bodiesbyrachel.vnfitnessapp.adapters.ProfileAdapter
import com.bodiesbyrachel.vnfitnessapp.models.ProfileResponse
import com.bodiesbyrachel.vnfitnessapp.utils.GeneralFunction
import com.bodiesbyrachel.vnfitnessapp.utils.ItemClickListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment(), ItemClickListener {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val profileFragment = ProfileFragment()
            profileFragment.arguments = bundle
            return profileFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

    }

    private fun setAdapter() {
        val profileResponse = Gson().fromJson(
            GeneralFunction.loadJSONFromAsset(activity!!, "profile_data.json"),
            ProfileResponse::class.java
        )

        rvProfile.layoutManager = LinearLayoutManager(activity)
        val profileAdapter = ProfileAdapter(activity!!, profileResponse.profileData!!, this)
        rvProfile.adapter = profileAdapter
    }

    override fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?) {
        when (viewId) {
            R.id.ivBack -> (activity as DashboardActivity).onBackPressed()
        }
    }

}
