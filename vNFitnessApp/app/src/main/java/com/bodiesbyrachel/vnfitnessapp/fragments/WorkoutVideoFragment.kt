package com.bodiesbyrachel.vnfitnessapp.fragments

import android.app.Dialog
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.activities.SelectedWorkoutActivity
import com.bodiesbyrachel.vnfitnessapp.models.Data
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_workout_video.*
import kotlinx.android.synthetic.main.item_cooldown.*
import java.util.*
import kotlin.concurrent.timerTask

/**
 * A simple [Fragment] subclass.
 */
class WorkoutVideoFragment : Fragment(), View.OnClickListener {

    var mainTimer: Timer? = null
    private var coolDownDuration = 11
    private var workoutDuration = 15
    private var workoutVideoList: List<Data>? = null
    private var totalRounds: Int? = null
    private var currentRound = 0
    private var totalExercise: Int? = null
    private var currentExercise = 0

    private var mediaPlayer: MediaPlayer? = null

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val workoutVideoFragment = WorkoutVideoFragment()
            workoutVideoFragment.arguments = bundle
            return workoutVideoFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val type = object : TypeToken<List<Data>>() {}.type
        workoutVideoList =
            Gson().fromJson(arguments!!.getString(Constants.WORKOUT_VIDEO_DATA), type)
        listener()
        totalRounds = workoutVideoList!![0].round!!.split(" ")[0].toInt()
        totalExercise = workoutVideoList!!.size

        showCoolDownDialog()

    }


    private fun showCoolDownDialog() {
        val coolDownDialog = Dialog(activity!!, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        coolDownDialog.setContentView(R.layout.item_cooldown)
        coolDownDialog.setCancelable(false)
        coolDownDialog.show()

        mainTimer = Timer()
        mainTimer!!.schedule(timerTask {
            activity!!.runOnUiThread {
                coolDownDuration--
                coolDownDialog.tvDialogTimer.text = coolDownDuration.toString()
                if (coolDownDuration == 0) {
                    mainTimer!!.cancel()
                    coolDownDuration = 11
                    coolDownDialog.dismiss()
                    showExercise()
                }

            }
        }, 0, 1000)
    }

    private fun showExercise() {
        tvExerciseName.text = workoutVideoList!![currentExercise].exercise
        tvRound.text = getString(R.string.round).plus(" ").plus(currentRound + 1)
        workoutDuration = workoutVideoList!![currentExercise].duration!!.split(" ")[0].toInt()

        if (currentExercise + 1 != workoutVideoList!!.size) {
            clNextExercise.visibility = View.VISIBLE
            tvNextExercise.text = workoutVideoList!![currentExercise + 1].exercise
        } else
            clNextExercise.visibility = View.GONE

        playVideo()
        startExerciseTimer()

    }

    private fun startExerciseTimer() {
        mainTimer = Timer()
        mainTimer!!.schedule(timerTask {
            activity!!.runOnUiThread {

                workoutDuration--
                checkTime()

                if (workoutDuration == 0) {
                    mainTimer!!.cancel()
                    currentExercise++

                    if (currentExercise == workoutVideoList!!.size) {
                        currentExercise = 0
                        currentRound++
                        if (currentRound == totalRounds) {
                            currentRound = 0
                            (activity as SelectedWorkoutActivity).onBackPressed()
                            mainTimer!!.cancel()
                        } else
                            showCoolDownDialog()
                    } else {
                        showCoolDownDialog()
                    }
                }
            }
        }, 0, 1000)
    }

    private fun checkTime() {
        if (workoutDuration >= 10)
            tvTimer.text = getString(R.string.zero_1).plus(workoutDuration.toString())
        else
            tvTimer.text = getString(R.string.zero_2).plus(workoutDuration.toString())
    }

    private fun stopExerciseTimer() {
        mainTimer!!.cancel()
    }

    private fun playVideo() {
        ivThumb.visibility = View.GONE
        vvExercise.setVideoURI(Uri.parse("android.resource://${activity!!.packageName}/${R.raw.video1}"))
        vvExercise.start()
        vvExercise.setOnPreparedListener { mp ->
            mediaPlayer = mp
            mp?.isLooping = true
            mp?.setVolume(0f, 0f)
        }
    }

    private fun pauseVideo() {
        mediaPlayer?.pause()
    }

    private fun listener() {
        ivBack.setOnClickListener(this)
        ivPlay.setOnClickListener(this)
        tvNext.setOnClickListener(this)
        tvExit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivBack -> {
                (activity as SelectedWorkoutActivity).onBackPressed()
                mainTimer!!.cancel()
            }
            R.id.tvExit -> {
                (activity as SelectedWorkoutActivity).onBackPressed()
                mainTimer!!.cancel()
            }
            R.id.ivPlay -> {
                if (ivPlay.tag == getString(R.string.play)) {
                    startExerciseTimer()
                    playVideo()
                    ivPlay.setImageResource(R.drawable.ic_action_pause)
                    ivPlay.tag = getString(R.string.pause)
                } else {
                    stopExerciseTimer()
                    pauseVideo()
                    ivPlay.setImageResource(R.drawable.ic_action_play_arrow)
                    ivPlay.tag = getString(R.string.play)
                }

            }
            R.id.tvNext -> {
                ivPlay.setImageResource(R.drawable.ic_action_pause)
                ivPlay.tag = getString(R.string.pause)

                mainTimer!!.cancel()

                currentExercise++
                if (currentExercise == workoutVideoList!!.size) {
                    currentExercise = 0
                    currentRound++
                    if (currentRound == totalRounds) {
                        currentRound = 0
                        (activity as SelectedWorkoutActivity).onBackPressed()
                        mainTimer!!.cancel()
                    } else
                        showExercise()
                } else
                    showExercise()
            }
        }
    }

}
