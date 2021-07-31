package com.example.exoplayer

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoUri = Uri.parse("https://i.imgur.com/7bMqysJ.mp4")
        val player = ExoPlayerFactory.newSimpleInstance(this, DefaultTrackSelector())
        playerView.player = player

        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(
                this,
                Util.getUserAgent(
                    this,
                    getString(R.string.app_name)
                )
            )
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse("https://i.imgur.com/7bMqysJ.mp4"))

        player.prepare(mediaSource)
        player.playWhenReady = true

/*        btnFullScreen.setOnClickListener {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }*/

    }
}