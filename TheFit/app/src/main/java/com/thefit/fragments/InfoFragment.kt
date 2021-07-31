package com.thefit.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thefit.R
import com.thefit.activities.WeightActivity
import com.thefit.adapters.InfoAdapter
import com.thefit.utils.ItemClickListener
import kotlinx.android.synthetic.main.fragment_info.*


class InfoFragment : Fragment(), ItemClickListener {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val infoFragment = InfoFragment()
            infoFragment.arguments = bundle
            return infoFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setAdapter()

    }

    private fun init() {
        rvInfo.layoutManager = LinearLayoutManager(activity!!)
    }

    private fun setAdapter() {
        rvInfo.adapter = InfoAdapter(activity!!,this)
    }

    override fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?) {
        when (viewId) {
            R.id.ivMenu -> Toast.makeText(activity!!, activity!!.getString(R.string.menu_clicked), Toast.LENGTH_SHORT).show()
            R.id.tvViewHistory->activity!!.startActivity(Intent(activity!!,WeightActivity::class.java))
        }
    }


}