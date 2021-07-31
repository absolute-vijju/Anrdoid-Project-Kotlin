package com.example.imagecrop.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.imagecrop.BuildConfig
import com.example.imagecrop.R
import com.example.imagecrop.utils.Constants
import com.example.imagecrop.utils.GeneralFunctions
import com.steelkiwi.cropiwa.CropIwaView
import com.steelkiwi.cropiwa.config.CropIwaSaveConfig
import kotlinx.android.synthetic.main.fragment_crop_iwa.*
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 */
class CropIwaFragment : Fragment(), View.OnClickListener {

    var capturedImageUri: Uri? = null
    var destinationUri: Uri? = null

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val cropIwaFragment = CropIwaFragment()
            cropIwaFragment.arguments = bundle
            return cropIwaFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_crop_iwa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        cropView.setCropSaveCompleteListener(object : CropIwaView.CropSaveCompleteListener {
            override fun onCroppedRegionSaved(bitmapUri: Uri?) {
                Log.d("mydata", "BitmapUri: ${bitmapUri!!.path}")
            }
        })

    }

    private fun init() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.using_cropIwa)
        btnOpenGallery.setOnClickListener(this)
        btnOpenCamera.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnOpenCamera -> {
                val file = GeneralFunctions.createFile(activity!!)
                capturedImageUri = Uri.fromFile(file)
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                cameraIntent.putExtra(
                    MediaStore.EXTRA_OUTPUT,
                    FileProvider.getUriForFile(
                        activity!!,
                        BuildConfig.APPLICATION_ID + ".provider",
                        file
                    )
                )
                startActivityForResult(cameraIntent, Constants.CAMERA_REQUEST_CODE)
            }
            R.id.btnOpenGallery -> {
                val galleryIntent = Intent(Intent.ACTION_PICK)
                galleryIntent.type = "image/*"
                val mimeType = arrayOf("image/jpeg", "image/png", "image/jpg")
                galleryIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
                startActivityForResult(galleryIntent, Constants.GALLERY_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, imageUri)
            cropView.setImage(bitmap)
        }
        if (requestCode == Constants.CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            try {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(activity!!.contentResolver, capturedImageUri)
                cropView.setImage(bitmap)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.my_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mDone -> {
                /*val fileName = "CROP_${System.currentTimeMillis()}.jpg"
                val file = File(activity!!.filesDir, fileName)

                val destinationUri = FileProvider.getUriForFile(
                    activity!!,
                    BuildConfig.APPLICATION_ID + ".provider",
                    file
                )

                cropView.crop(
                    CropIwaSaveConfig.Builder(destinationUri)
                        .setCompressFormat(Bitmap.CompressFormat.JPEG)
                        .setQuality(90)
                        .build()
                )*/
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
