package com.example.imagecrop.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.imagecrop.R
import com.example.imagecrop.utils.Constants
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        init()
        askCameraAndStoragePermission()
        checkExternalMedia()
        val result = filesDir
        Log.d("mydata", result!!.absolutePath)
    }

    private fun init() {
        btnScissorsLibrary.setOnClickListener(this)
        btnCropMe.setOnClickListener(this)
        btnCropIwa.setOnClickListener(this)
        btnSimpleCropView.setOnClickListener(this)
        btnAndroidImageCropper.setOnClickListener(this)
        btnCropperNoCropper.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnScissorsLibrary -> {
                val intent = Intent(this, CropActivity::class.java)
                intent.putExtra(Constants.LIBRARY_TYPE, Constants.LibraryType.Scissors)
                startActivity(intent)
            }
            R.id.btnCropMe -> {
                val intent = Intent(this, CropActivity::class.java)
                intent.putExtra(Constants.LIBRARY_TYPE, Constants.LibraryType.CropMe)
                startActivity(intent)
            }
            R.id.btnCropIwa -> {
                val intent = Intent(this, CropActivity::class.java)
                intent.putExtra(Constants.LIBRARY_TYPE, Constants.LibraryType.CropIwa)
                startActivity(intent)
            }
            R.id.btnSimpleCropView -> {
                val intent = Intent(this, CropActivity::class.java)
                intent.putExtra(Constants.LIBRARY_TYPE, Constants.LibraryType.SimpleCropView)
                startActivity(intent)
            }
            R.id.btnAndroidImageCropper -> {
                val intent = Intent(this, CropActivity::class.java)
                intent.putExtra(Constants.LIBRARY_TYPE, Constants.LibraryType.Android_Image_Cropper)
                startActivity(intent)
            }
            R.id.btnCropperNoCropper -> {
                val intent = Intent(this, CropActivity::class.java)
                intent.putExtra(Constants.LIBRARY_TYPE, Constants.LibraryType.Cropper_NoCropper)
                startActivity(intent)
            }
        }
    }

    private fun checkExternalMedia() {
        var mExternalStorageAvailable = false
        var mExternalStorageWriteable = false
        val state: String = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // Can read and write the media
            mExternalStorageWriteable = true
            mExternalStorageAvailable = mExternalStorageWriteable
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // Can only read the media
            mExternalStorageAvailable = true
            mExternalStorageWriteable = false
        } else {
            // Can't read or write
            mExternalStorageWriteable = false
            mExternalStorageAvailable = mExternalStorageWriteable
        }
        Log.d(
            "mydata",
            "External Media: readable=$mExternalStorageAvailable writable=$mExternalStorageWriteable"
        )
    }

    private fun askCameraAndStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                Constants.CAMERA_PERMISSION_REQUEST_CODE
            )
        }
    }
}
