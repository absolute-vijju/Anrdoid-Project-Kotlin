package com.bodiesbyrachel.vnfitnessapp.fragments

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.activities.LoginActivity
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import com.bodiesbyrachel.vnfitnessapp.utils.SharedPreference
import kotlinx.android.synthetic.main.fragment_intro_video.*

class IntroVideoFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val introVideoFragment = IntroVideoFragment()
            introVideoFragment.arguments = bundle
            return introVideoFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playVideo()
    }

    private fun playVideo() {
        vvIntro.setVideoURI(Uri.parse("android.resource://${activity!!.packageName}/${R.raw.gym_intro}"))
        vvIntro.start()
        vvIntro.setOnCompletionListener {
            (activity as LoginActivity).addFragmentFromActivity(WelcomeFragment.newInstance(null))
            SharedPreference.save(activity!!, Constants.SHOW_INTRO_VIDEO, false)
        }
    }

}