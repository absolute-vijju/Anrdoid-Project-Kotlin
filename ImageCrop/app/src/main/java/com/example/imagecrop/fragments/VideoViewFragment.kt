package com.example.imagecrop.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity

import com.example.imagecrop.R
import com.example.imagecrop.utils.Constants
import kotlinx.android.synthetic.main.fragment_video_view.*

/**
 * A simple [Fragment] subclass.
 */
class VideoViewFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val videoViewFragment =
                VideoViewFragment()
            videoViewFragment.arguments = bundle
            return videoViewFragment
        }
    }

    private var mediaController: MediaController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() {
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.using_video_view)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mediaController = MediaController(activity)
        btnOpenGallery.setOnClickListener(this)
        btnLoadFromAsset.setOnClickListener(this)
        btnLoadFromUrl.setOnClickListener(this)
    }

    private fun playVideoFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "video/*"
        startActivityForResult(
            Intent.createChooser(intent, getString(R.string.select_video_from)),
            Constants.VIDEO_REQUEST_CODE
        )
    }

    private fun playVideoFromAssets() {
        val videoUri =
            Uri.parse("android.resource://${activity!!.packageName}/${R.raw.sample_video}")
        playVideo(videoUri)
    }

    private fun playVideoFromUrl() {
        val videoUri =
            Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        playVideo(videoUri)
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

    private fun playVideo(videoUri: Uri) {
        vv.setMediaController(mediaController)
        mediaController?.setAnchorView(vv)
        vv.setVideoURI(videoUri)
        vv.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constants.VIDEO_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val videoUri = data.data
            if (videoUri != null)
                playVideo(videoUri)
        }

    }
}