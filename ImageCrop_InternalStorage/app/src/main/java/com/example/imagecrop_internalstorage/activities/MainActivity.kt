package com.example.imagecrop.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.imagecrop.BuildConfig
import com.example.imagecrop.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var cameraFilePath: String

    companion object {
        const val GALLERY_REQUEST_CODE = 101
        const val CAMERA_REQUEST_CODE = 102
        const val CAMERA_PERMISSION_REQUEST_CODE = 103
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }

    private fun init() {
        btnOpenGallery.setOnClickListener(this)
        btnOpenCamera.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnOpenGallery -> {
                val galleryIntent = Intent(Intent.ACTION_PICK)
                galleryIntent.type = "image/*"
                val mimeType = arrayOf("image/jpeg", "image/png", "image/jpg")
                galleryIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
                startActivityForResult(galleryIntent,
                    GALLERY_REQUEST_CODE
                )
            }
            R.id.btnOpenCamera -> {
                askCameraPermission()
            }
        }
    }

    private fun askCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        } else
            openCamera()
    }

    private fun openCamera() {
        try {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraIntent.putExtra(
                MediaStore.EXTRA_OUTPUT,
                FileProvider.getUriForFile(
                    this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    createImageFile()
                )
            )
            startActivityForResult(cameraIntent,
                CAMERA_REQUEST_CODE
            )
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GALLERY_REQUEST_CODE -> {
                    val selectedImageUri = data!!.data
                    ivSelectedImage.setImageURI(selectedImageUri)
                }
                CAMERA_REQUEST_CODE -> {
                    ivSelectedImage.setImageURI(Uri.parse(cameraFilePath))
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            openCamera()
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_$timeStamp _"
        val storageDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
            "Camera"
        )
        val image = File.createTempFile(imageFileName, ".jpg", storageDir)
        cameraFilePath = image.absolutePath
        return image
    }

    private fun getAbsolutePathFromUri(uri: Uri): String {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor =
            contentResolver.query(uri, filePathColumn, null, null, null)
        cursor!!.moveToFirst()
        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        val imageDecodableString = cursor.getString(columnIndex)
        cursor.close()
        return imageDecodableString
    }

}
