package com.example.exoplayer

import android.net.Uri
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class ExoPlayerHelper(
    private val playerView: PlayerView,
    onError: (ExoPlaybackException) -> Unit,
    onPlayerBuffer: (Boolean) -> Unit
) {

    private var exoPlayer: ExoPlayer? = null
    private var mediaSource: ProgressiveMediaSource? = null

    private val playerListener = object : Player.EventListener {
        override fun onPlayerError(error: ExoPlaybackException) {
//            super.onPlayerError(error)
            onError(error)
        }

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
//            super.onPlayerStateChanged(playWhenReady, playbackState)
            onPlayerBuffer(playbackState == Player.STATE_BUFFERING)
        }
    }

    fun initializePlayer(url: String) {
        exoPlayer = SimpleExoPlayer.Builder(playerView.context).build()
        exoPlayer!!.repeatMode = Player.REPEAT_MODE_ALL
        exoPlayer!!.addListener(playerListener)

        playerView.player = exoPlayer

        val userAgent =
            Util.getUserAgent(playerView.context, playerView.context.getString(R.string.app_name))
        mediaSource = ProgressiveMediaSource
            .Factory(
                DefaultDataSourceFactory(playerView.context, userAgent),
                DefaultExtractorsFactory()
            )
            .createMediaSource(Uri.parse(url))

        exoPlayer!!.prepare(mediaSource!!, true, false)
        exoPlayer!!.playWhenReady = true
    }

    private fun killPlayer() {
        if (exoPlayer != null) {
            exoPlayer!!.release()
            exoPlayer = null
            mediaSource = null
            playerView.player = null
        }
    }
}