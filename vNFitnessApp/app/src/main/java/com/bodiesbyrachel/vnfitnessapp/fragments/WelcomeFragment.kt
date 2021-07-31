package com.bodiesbyrachel.vnfitnessapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.activities.DashboardActivity
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import com.bodiesbyrachel.vnfitnessapp.utils.SharedPreference
import kotlinx.android.synthetic.main.fragment_welcome.*

/**
 * A simple [Fragment] subclass.
 */
class WelcomeFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val welcomeFragment = WelcomeFragment()
            welcomeFragment.arguments = bundle
            return welcomeFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeners()

    }

    private fun listeners() {
        btnLogin.setOnClickListener(this)
        btnCreateAccount.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLogin -> {
                SharedPreference.save(activity!!, Constants.LOGIN_STATUS, true)
                activity!!.startActivity(Intent(activity!!, DashboardActivity::class.java))
                activity!!.finish()
            }
            R.id.btnCreateAccount -> {
                activity!!.startActivity(Intent(activity!!, DashboardActivity::class.java))
                activity!!.finish()
            }
        }
    }

}
