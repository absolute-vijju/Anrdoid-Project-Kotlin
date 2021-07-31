package com.example.imagecrop.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.imagecrop.R
import com.example.imagecrop.utils.Constants
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.fragment_exo.*


/**
 * A simple [Fragment] subclass.
 */
class ExoFragment : Fragment(), View.OnClickListener {

    private var simpleExoPlayer: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val exoFragment = ExoFragment()
            exoFragment.arguments = bundle
            return exoFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun initializePlayer(videoUri: Uri) {
        simpleExoPlayer = SimpleExoPlayer.Builder(activity!!).build()
        simpleExoPLayerView.player = simpleExoPlayer
        val mediaSource = buildMediaSource(videoUri)
        simpleExoPlayer?.playWhenReady = playWhenReady;
        simpleExoPlayer?.seekTo(currentWindow, playbackPosition)
        if (mediaSource != null)
            simpleExoPlayer?.prepare(mediaSource, false, false);
    }

    private fun buildMediaSource(uri: Uri): MediaSource? {
        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(activity, "exoplayer-codelab")
        return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
    }

    private fun releasePlayer() {
        if (simpleExoPlayer != null) {
            playWhenReady = simpleExoPlayer!!.playWhenReady
            playbackPosition = simpleExoPlayer!!.currentPosition
            currentWindow = simpleExoPlayer!!.currentWindowIndex
            simpleExoPlayer!!.release()
            simpleExoPlayer = null
        }
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun playVideoFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "video/*"
        startActivityForResult(
            Intent.createChooser(intent, getString(R.string.select_video_from)),
            Constants.VIDEO_REQUEST_CODE
        )
    }

    private fun init() {
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.using_exo_player)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btnOpenGallery.setOnClickListener(this)
        btnLoadFromAsset.setOnClickListener(this)
        btnLoadFromUrl.setOnClickListener(this)
    }

    private fun playVideoFromAssets() {
//        val videoUri = Uri.parse("android.resource://${activity!!.packageName}/${R.raw.sample_video}")
        val videoUri = Uri.parse("asset:///${R.raw.sample_video}")
        initializePlayer(videoUri)
    }

    private fun playVideoFromUrl() {
        val videoUri =
            Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        initializePlayer(videoUri)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnOpenGallery -> {
                playVideoFromGallery()
            }
            R.id.btnLoadFromAsset -> {
                playVideoFromAssets()
            }
            R.id.btnLoadFromUrl -> {
                playVideoFromUrl()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.VIDEO_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val videoUri = data.data
            if (videoUri != null)
                initializePlayer(videoUri)
        }
    }

}
