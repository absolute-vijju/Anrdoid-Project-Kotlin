package com.example.imagepicker

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.yalantis.ucrop.UCrop
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var currentPhotoPath: String

    companion object {
        const val TAKE_PHOTO_CODE = 0
        const val CHOOSE_PHOTO_CODE = 1
        const val CAMERA_PERMISSION_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivPick.setOnClickListener(this)

    }

    private fun selectImage(activity: Activity) {
        val options = arrayOf<String>(
            getString(R.string.take__photo),
            getString(R.string.choose_from_gallery)
        )
        val builder =
            AlertDialog.Builder(activity)
        builder.setTitle(getString(R.string.add_photo))
        builder.setItems(options) { dialog, item ->
            if (options[item] == getString(R.string.take__photo)) {
                askCameraPermission()
            } else if (options[item] == getString(R.string.choose_from_gallery)) {
                val pickPhoto = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(pickPhoto, CHOOSE_PHOTO_CODE)
            }
        }
        builder.show()
    }

    private fun askCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        else
            dispatchTakePictureIntent()
    }

    override fun onClick(v: View?) {
        selectImage(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TAKE_PHOTO_CODE && resultCode == Activity.RESULT_OK) {
            val file = File(currentPhotoPath)
            startCrop(Uri.fromFile(file))
        }
        if (requestCode == CHOOSE_PHOTO_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val contentUri = data.data
            startCrop(contentUri!!)
        }
        if (requestCode == UCrop.REQUEST_CROP && resultCode == Activity.RESULT_OK) {
            val uri = UCrop.getOutput(data!!)
            ivPick?.setImageURI(uri)
        }

    }

    private fun getFileExtension(contentUri: Uri): String? {
        val mimeType = MimeTypeMap.getSingleton()
        return mimeType.getExtensionFromMimeType(contentResolver.getType(contentUri))

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults.isNotEmpty())
            dispatchTakePictureIntent()
        else
            Toast.makeText(this, "Camera Permission Required", Toast.LENGTH_SHORT).show()
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.example.android.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, TAKE_PHOTO_CODE)
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private fun startCrop(uri: Uri) {
        val imageFileName =
            "JPEG_${SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())}_.${getFileExtension(uri)}"

        val uCrop = UCrop.of(uri, Uri.fromFile(File(cacheDir, imageFileName)))
        uCrop.withAspectRatio(1f, 1f)
        uCrop.withMaxResultSize(450, 450)
        uCrop.withOptions(getCropOption())
        uCrop.start(this)

    }

    private fun getCropOption(): UCrop.Options {
        val option = UCrop.Options()
        option.setCompressionQuality(70)
        option.setCompressionFormat(Bitmap.CompressFormat.JPEG)
        option.setHideBottomControls(false)
        option.setFreeStyleCropEnabled(true)
        option.setStatusBarColor(getColor(R.color.colorPrimaryDark))
        option.setToolbarColor(getColor(R.color.colorPrimary))
        option.setToolbarTitle("Crop Image Title")
        return option
    }

}

