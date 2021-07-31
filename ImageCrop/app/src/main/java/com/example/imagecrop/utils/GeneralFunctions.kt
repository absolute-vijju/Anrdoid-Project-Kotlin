package com.example.imagecrop.utils

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.example.imagecrop.R
import com.example.imagecrop.activities.CropActivity
import com.example.imagecrop.fragments.ImageResultFragment
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream


class GeneralFunctions {
    companion object {

        private fun showResult(activity: Activity, imagePath: String?) {
            if (activity is CropActivity) {
                if (imagePath != null) {
                    val bundle = Bundle()
                    bundle.putString(Constants.IMAGE_PATH, imagePath)
                    activity.replaceFragmentWithBackStack(
                        ImageResultFragment.newInstance(
                            bundle
                        )
                    )
                } else
                    Toast.makeText(
                        activity,
                        activity.getString(R.string.unknown_path),
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }

        fun createFileFromBitmap(bitmap: Bitmap, activity: Activity) {
            var result: String? = null
            val fileName = "CROP_${System.currentTimeMillis()}.jpg"
            var fileOutputStream: FileOutputStream? = null
            try {
                fileOutputStream = activity.openFileOutput(fileName, MODE_PRIVATE)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream)
                Toast.makeText(
                    activity,
                    activity.getString(R.string.imaged_saved),
                    Toast.LENGTH_SHORT
                ).show()
                result = "${activity.filesDir}/$fileName"
            } catch (e: Exception) {
                Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
                e.printStackTrace()
                result = null
            } finally {
                fileOutputStream?.flush()
                fileOutputStream?.close()
                showResult(activity, result)
            }
        }

        fun createFile(activity: Activity): File {
            val fileName = "CAMERA_${System.currentTimeMillis()}.jpg"
            val file = File(activity.filesDir, fileName)

            Log.d("mydata", "Camera Saving image path: ${file.absolutePath}")

            if (file.exists())
                file.delete()

            return file
        }

        /**
         * If you want to save file into external public storage then uncomment thins block.
         * Don't forget to change file provider in manifest file
         */
        /*fun createFile(activity: Activity): File {
            val rootPath =
                Environment.getExternalStorageDirectory()
                    .toString().plus("/Cropped_Images")

            val rootDir = File(rootPath)
            if (!rootDir.exists())
                rootDir.mkdir()

            val fileName = "CAMERA_${System.currentTimeMillis()}.jpg"
            val file = File(rootDir, fileName)

            Log.d("mydata", file.absolutePath)

            if (file.exists())
                file.delete()

            return file
        }
        fun createFileFromBitmap(bitmap: Bitmap, activity: Activity) {
            val rootPath =
                Environment.getExternalStorageDirectory()
                    .toString().plus("/Cropped_Images")

            val rootDir = File(rootPath)
            if (!rootDir.exists())
                rootDir.mkdir()

            val fileName = "CROP_${System.currentTimeMillis()}.jpg"
            val file = File(rootDir, fileName)

            Log.d("absolutePath", file.absolutePath)

            if (file.exists())
                file.delete()

            try {
                val fileOutputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream)
                Toast.makeText(
                    activity,
                    activity.getString(R.string.imaged_saved),
                    Toast.LENGTH_SHORT
                ).show()
                fileOutputStream.flush()
                fileOutputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            MediaScannerConnection.scanFile(
                activity,
                arrayOf(file.path),
                arrayOf("image/jpg"),
                null
            )
        }*/
    }

}