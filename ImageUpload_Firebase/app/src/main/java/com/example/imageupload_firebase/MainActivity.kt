package com.example.imageupload_firebase

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCanceledListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val storageReference = FirebaseStorage.getInstance().reference
    private var imageUri: Uri? = null
    private val imageList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }

    private fun init() {
        btnPickImage.setOnClickListener(this)
        btnUpload.setOnClickListener(this)
    }

    private fun openGallery() {
        val galleryIntent = Intent()
        galleryIntent.type = "image/*"
        galleryIntent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(galleryIntent, Constants.GALLERY_REQUEST_CODE)

    }

    private fun uploadImageToFirebase() {
        val fileName = "IMG_${System.currentTimeMillis()}"
        val result = storageReference.child("images/$fileName")
        result.putFile(imageUri!!)
            .addOnSuccessListener {
                progressBar.visibility = View.GONE
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.image_uploaded),
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnCanceledListener {
                progressBar.visibility = View.GONE
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.failed_to_upload),
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnProgressListener { p0 ->
                progressBar.visibility = View.VISIBLE
                val progress = (p0.bytesTransferred / p0.totalByteCount) * 100
                Log.d("mydata", progress.toString())
            }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnPickImage -> {
                openGallery()
            }
            R.id.btnUpload -> {
                if (imageUri != null)
                    uploadImageToFirebase()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            ivSelectedImage.setImageBitmap(bitmap)
        }
    }


}
