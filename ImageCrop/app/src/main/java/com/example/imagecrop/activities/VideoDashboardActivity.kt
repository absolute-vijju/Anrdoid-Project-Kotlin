package com.example.imagecrop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.imagecrop.R
import com.example.imagecrop.utils.Constants
import kotlinx.android.synthetic.main.activity_video_dashboard.*

class VideoDashboardActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_dashboard)

        init()

    }

    private fun init() {
        supportActionBar?.title = getString(R.string.video_activity)
        btnVideoView.setOnClickListener(this)
        btnExoPlayer.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnVideoView -> {
                val intent = Intent(this, VideoActivity::class.java)
                intent.putExtra(Constants.LIBRARY_TYPE, Constants.LibraryType.VideoView)
                startActivity(intent)
            }
            R.id.btnExoPlayer -> {
                val intent = Intent(this, VideoActivity::class.java)
                intent.putExtra(Constants.LIBRARY_TYPE, Constants.LibraryType.ExoPlayer)
                startActivity(intent)
            }
        }
    }
}
