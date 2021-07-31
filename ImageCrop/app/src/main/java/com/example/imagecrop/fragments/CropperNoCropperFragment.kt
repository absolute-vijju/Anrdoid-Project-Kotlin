package com.example.imagecrop.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.imagecrop.BuildConfig

import com.example.imagecrop.R
import com.example.imagecrop.utils.Constants
import com.example.imagecrop.utils.GeneralFunctions
import kotlinx.android.synthetic.main.fragment_crop_me.*
import kotlinx.android.synthetic.main.fragment_cropper_no_cropper.*
import kotlinx.android.synthetic.main.fragment_cropper_no_cropper.btnOpenCamera
import kotlinx.android.synthetic.main.fragment_cropper_no_cropper.btnOpenGallery
import kotlinx.android.synthetic.main.fragment_cropper_no_cropper.cropView
import kotlinx.android.synthetic.main.fragment_scissors.*
import java.io.FileNotFoundException
import java.io.IOException
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class CropperNoCropperFragment : Fragment(), View.OnClickListener {

    var capturedImageUri: Uri? = null

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val cropperNoCropperFragment = CropperNoCropperFragment()
            cropperNoCropperFragment.arguments = bundle
            return cropperNoCropperFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_cropper_no_cropper, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.using_cropperNoCropper)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
            cropView.setImageBitmap(bitmap)
        }
        if (requestCode == Constants.CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            try {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(activity!!.contentResolver, capturedImageUri)
                cropView.setImageBitmap(bitmap)
            } catch (e: Exception) {
                Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
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
                val croppedImage = cropView.croppedBitmap
                val croppedBitmap=croppedImage.bitmap
                if (croppedBitmap != null)
                    GeneralFunctions.createFileFromBitmap(croppedBitmap, activity!!)
                else
                    Toast.makeText(activity, activity!!.getString(R.string.no_bitmap_found), Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}