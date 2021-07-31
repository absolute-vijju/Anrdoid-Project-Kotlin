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
import com.isseiaoki.simplecropview.CropImageView
import com.isseiaoki.simplecropview.callback.CropCallback
import kotlinx.android.synthetic.main.fragment_scissors.*
import kotlinx.android.synthetic.main.fragment_simple_crop_view.*
import kotlinx.android.synthetic.main.fragment_simple_crop_view.btnOpenCamera
import kotlinx.android.synthetic.main.fragment_simple_crop_view.btnOpenGallery
import kotlinx.android.synthetic.main.fragment_simple_crop_view.cropView
import java.io.FileNotFoundException
import java.io.IOException
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class SimpleCropViewFragment : Fragment(), View.OnClickListener {

    var sourceUri: Uri? = null
    var capturedImageUri: Uri? = null

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val simpleCropViewFragment = SimpleCropViewFragment()
            simpleCropViewFragment.arguments = bundle
            return simpleCropViewFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_simple_crop_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        cropView.setCropMode(CropImageView.CropMode.SQUARE) // set crop mode like FIT_IMAGE, RATIO_4_3, RATIO_3_4, SQUARE(default), RATIO_16_9, RATIO_9_16, FREE, CUSTOM, CIRCLE, CIRCLE_SQUARE
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.using_simpleCropView)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btnOpenGallery.setOnClickListener(this)
        btnOpenCamera.setOnClickListener(this)
        ivRotateClockWise.setOnClickListener(this)
        ivRotateCounterClockWise.setOnClickListener(this)
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
            R.id.ivRotateClockWise -> {
                try {
                    // rotate clockwise by 90 degrees
                    cropView.rotateImage(CropImageView.RotateDegrees.ROTATE_90D)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            R.id.ivRotateCounterClockWise -> {
                try {
                    // rotate counter-clockwise by 90 degrees
                    cropView.rotateImage(CropImageView.RotateDegrees.ROTATE_M90D)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        cropView
        if (requestCode == Constants.GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri = data.data
            sourceUri = imageUri
            val bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, imageUri)
            cropView.imageBitmap = bitmap
        }
        if (requestCode == Constants.CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            try {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(activity!!.contentResolver, capturedImageUri)
                cropView.imageBitmap = bitmap
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
                cropView.crop(sourceUri).execute(object : CropCallback {
                    override fun onSuccess(cropped: Bitmap?) {
                        GeneralFunctions.createFileFromBitmap(cropped!!, activity!!)
                    }

                    override fun onError(e: Throwable?) {
                        Toast.makeText(
                            activity,
                            activity!!.getString(R.string.no_bitmap_found),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
