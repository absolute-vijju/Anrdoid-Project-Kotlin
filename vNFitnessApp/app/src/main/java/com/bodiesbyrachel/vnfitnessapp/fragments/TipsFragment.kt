package com.bodiesbyrachel.vnfitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2

import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.adapters.TipsAdapter
import com.bodiesbyrachel.vnfitnessapp.models.TipsResponse
import com.bodiesbyrachel.vnfitnessapp.utils.GeneralFunction
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_tips.*
import kotlinx.android.synthetic.main.header.*

/**
 * A simple [Fragment] subclass.
 */
class TipsFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val tipsFragment = TipsFragment()
            tipsFragment.arguments = bundle
            return tipsFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tips, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTitle.text = getString(R.string.tips_and_info).toUpperCase()

        val tipsResponse = Gson().fromJson(
            GeneralFunction.loadJSONFromAsset(activity!!, "tips_data.json"),
            TipsResponse::class.java
        )

        val tipsAdapter = TipsAdapter(activity!!, tipsResponse.tipsData!!)
        tipsViewPager.adapter = tipsAdapter
//        tabLayout.setupWithViewPager(tipsViewPager)


        TabLayoutMediator(tabLayout, tipsViewPager) { tab, position ->
        }.attach()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
//                TODO("Not yet implemented")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                TODO("Not yet implemented")
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
//                TODO("Not yet implemented")
            }
        })

    }

}

