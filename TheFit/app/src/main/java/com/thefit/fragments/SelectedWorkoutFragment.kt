package com.thefit.fragments

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.gson.Gson
import com.thefit.R
import com.thefit.models.ExerciseData
import com.thefit.utils.Constants
import kotlinx.android.synthetic.main.custom_exo_control.*
import kotlinx.android.synthetic.main.fragment_selected_workout.*
import kotlinx.android.synthetic.main.header.*
import kotlinx.coroutines.*


class SelectedWorkoutFragment : Fragment(), View.OnClickListener {

    private var selectedWorkoutData: ExerciseData? = null
    private var isMute = false
    private var fullscreen = false
    private var simpleExoPlayer: SimpleExoPlayer? = null
    private var timeJob: Job? = null

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val selectedWorkoutFragment = SelectedWorkoutFragment()
            selectedWorkoutFragment.arguments = bundle
            return selectedWorkoutFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selected_workout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() {

        ivBack.setOnClickListener(this)
        ivExpand.setOnClickListener(this)
        ivSound.setOnClickListener(this)

        selectedWorkoutData = Gson().fromJson(
            arguments!!.getString(Constants.SELECTED_WORKOUT_DATA),
            ExerciseData::class.java
        )

        ivMenu.visibility = View.INVISIBLE
        ivBack.visibility = View.VISIBLE
        if (selectedWorkoutData != null) {
            setData(selectedWorkoutData!!)
        }
    }

    private fun setData(selectedWorkoutData: ExerciseData) {
        tvTitle.text = selectedWorkoutData.exerciseName
        tvSelectedWorkoutName.text = selectedWorkoutData.exerciseName
        tvWorkoutDesc.text = selectedWorkoutData.desc
        tvIncreaseDifficultyDesc.text = selectedWorkoutData.increaseDifficulty
        tvDecreaseDifficultyDesc.text = selectedWorkoutData.decreaseDifficulty
        tvPreviousPersonalBestDesc.text = selectedWorkoutData.previousPersonalBest
        tvAlternateExerciseDesc.text = selectedWorkoutData.alternateExercise
    }

    private fun playVideoFromUrl() {
        vvSelectedWorkout.visibility = View.INVISIBLE
        if (simpleExoPlayer == null)
            simpleExoPlayer = SimpleExoPlayer.Builder(activity!!).build()
        vvSelectedWorkout.player = simpleExoPlayer
//        val videoUri = RawResourceDataSource.buildRawResourceUri(R.raw.gym_intro)
        val videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")   // from url

        val defaultDataSourceFactory =
            DefaultDataSourceFactory(activity!!, Util.getUserAgent(activity!!, "exo_player"))
        val progressiveMediaSource =
            ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(videoUri)

        simpleExoPlayer?.prepare(progressiveMediaSource)
        simpleExoPlayer?.playWhenReady = false
        if (isMute)
            simpleExoPlayer?.volume = 0f
        else
            simpleExoPlayer?.volume = 1f

        simpleExoPlayer?.addListener(object : Player.EventListener {

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playbackState == ExoPlayer.STATE_READY) {
                    vvSelectedWorkout.visibility = View.VISIBLE
                    pb.visibility = View.GONE
                    val duration = simpleExoPlayer?.duration?.div(1000)
                    val position = simpleExoPlayer?.currentPosition?.div(1000)
                    timeJob?.cancel(null)
                    if (playWhenReady) {
                        if (duration != null && position != null) {
                            setDuration((duration - position).toInt())
                        }
                    }
                } else if (playbackState == ExoPlayer.STATE_BUFFERING) {
                    vvSelectedWorkout.visibility = View.VISIBLE
                    pb.visibility = View.VISIBLE
                } else if (playbackState == ExoPlayer.STATE_ENDED) {
                    vvSelectedWorkout.visibility = View.VISIBLE
                    pb.visibility = View.GONE
                    timeJob?.cancel(null)
                    exo_duration.text = "-00:00"
                }

            }

        })
    }

    private fun setDuration(durationInSeconds: Int) {
        timeJob = GlobalScope.launch {
            for (i in durationInSeconds downTo 0) {
                withContext(Dispatchers.Main) {
                    when {
                        i == 0 -> {
                            exo_duration.text = "-00:00"
                            cancel(null)
                        }
                        i in 1..9 -> exo_duration.text = "-00:0$i"
                        i in 10..59 -> exo_duration.text = "-00:$i"
                        i > 59 -> {
                            val quotient = i / 60   // minutes
                            val reminder = (i % 60) // remaining seconds
                            if (quotient in 1..9) {
                                when (reminder) {
                                    in 1..9 -> exo_duration.text = "-0$quotient:0$reminder"
                                    in 10..59 -> exo_duration.text = "-0$quotient:$reminder"
                                }

                            } else if (quotient in 10..59) {
                                when (reminder) {
                                    in 1..9 -> exo_duration.text = "-$quotient:0$reminder"
                                    in 10..59 -> exo_duration.text = "-$quotient:$reminder"
                                }
                            }
                        }
                    }
                }
                delay(1000)
            }
        }
    }

    fun releasePlayer() {
        timeJob?.cancel(null)
        simpleExoPlayer?.release()
        simpleExoPlayer = null
        simpleExoPlayer?.playWhenReady = false
    }

    private fun showLayouts() {
        header.visibility = View.VISIBLE
        ivThumbnail.visibility = View.VISIBLE
        tvSelectedWorkoutName.visibility = View.VISIBLE
        llProgressContainer.visibility = View.VISIBLE
        vSeparator.visibility = View.VISIBLE
        tvWeight.visibility = View.VISIBLE
        tvSet.visibility = View.VISIBLE
        vSeparator2.visibility = View.VISIBLE
        tvWorkoutDesc.visibility = View.VISIBLE
        tvIncreaseDifficulty.visibility = View.VISIBLE
        tvIncreaseDifficultyDesc.visibility = View.VISIBLE
        tvDecreaseDifficulty.visibility = View.VISIBLE
        tvDecreaseDifficultyDesc.visibility = View.VISIBLE
        tvPreviousPersonalBest.visibility = View.VISIBLE
        tvPreviousPersonalBestDesc.visibility = View.VISIBLE
        tvAlternateExercise.visibility = View.VISIBLE
        tvAlternateExerciseDesc.visibility = View.VISIBLE
        ivRightArrow.visibility = View.VISIBLE
    }

    private fun hideLayouts() {
        header.visibility = View.GONE
        ivThumbnail.visibility = View.GONE
        tvSelectedWorkoutName.visibility = View.GONE
        llProgressContainer.visibility = View.GONE
        vSeparator.visibility = View.GONE
        tvWeight.visibility = View.GONE
        tvSet.visibility = View.GONE
        vSeparator2.visibility = View.GONE
        tvWorkoutDesc.visibility = View.GONE
        tvIncreaseDifficulty.visibility = View.GONE
        tvIncreaseDifficultyDesc.visibility = View.GONE
        tvDecreaseDifficulty.visibility = View.GONE
        tvDecreaseDifficultyDesc.visibility = View.GONE
        tvPreviousPersonalBest.visibility = View.GONE
        tvPreviousPersonalBestDesc.visibility = View.GONE
        tvAlternateExercise.visibility = View.GONE
        tvAlternateExerciseDesc.visibility = View.GONE
        ivRightArrow.visibility = View.GONE
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ivBack -> activity!!.onBackPressed()
            R.id.ivExpand -> {

                if (fullscreen) {
                    showLayouts()
                    fullscreen = false
                    ivExpand.setImageDrawable(
                        ContextCompat.getDrawable(
                            activity!!,
                            R.drawable.ic_action_expand
                        )
                    )
                    activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    val params = vvSelectedWorkout.layoutParams as (ConstraintLayout.LayoutParams)
                    params.width = ConstraintLayout.LayoutParams.MATCH_PARENT
                    params.height = (250 * activity!!.resources.displayMetrics.density).toInt()
                    vvSelectedWorkout.layoutParams = params

                } else {
                    hideLayouts()
                    fullscreen = true
                    ivExpand.setImageDrawable(
                        ContextCompat.getDrawable(
                            activity!!,
                            R.drawable.ic_action_expand
                        )
                    )
                    activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    val params = vvSelectedWorkout.layoutParams as (ConstraintLayout.LayoutParams)
                    params.width = ConstraintLayout.LayoutParams.MATCH_PARENT
                    params.height = ConstraintLayout.LayoutParams.MATCH_PARENT
                    vvSelectedWorkout.layoutParams = params

                }
            }
            R.id.ivSound -> {
                if (isMute) {
                    ivSound.setImageResource(R.drawable.ic_action_volume)
                    isMute = false
                    simpleExoPlayer?.volume = 1f
                } else {
                    ivSound.setImageResource(R.drawable.ic_action_mute)
                    isMute = true
                    simpleExoPlayer?.volume = 0f
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onResume() {
        super.onResume()
        playVideoFromUrl()
    }
}