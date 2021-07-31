package com.example.internalandexternalstorage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * This is default path where file is stored
         * To test the file you need to test your app below api 21 device
         */
        Log.d("mydata", filesDir.absolutePath)

        showFileList()

        btnSave.setOnClickListener(this)
        btnRead.setOnClickListener(this)
        btnDelete.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {

            R.id.btnSave -> {
                saveImageFile()
            }

            R.id.btnRead -> {
                readImageFile()
            }

            R.id.btnDelete -> {
                deleteFile()
            }
        }
    }

    private fun deleteFile() {
        val fileName = etFileName.text.toString()
        deleteFile(fileName)
        Toast.makeText(this, "File deleted", Toast.LENGTH_SHORT).show()

    }

    private fun showFileList() {
        val fileList = fileList()

        for (i in fileList.indices) {
            tvFileContents.append(fileList[i].plus("\n"))
        }

    }

    private fun saveTextFile() {
        val fileName = etFileName.text.toString().plus(".txt")
        val fileContents = etFileContents.text.toString()

        var outputStream: OutputStream? = null

        try {
            /**
             * MODE_PRIVATE - This will not override old contents
             * MODE_APPEND - This will override old contents
             */
            outputStream = openFileOutput(fileName, Context.MODE_APPEND)
            outputStream.write(fileContents.toByteArray())
            outputStream.flush()
            Toast.makeText(this, getString(R.string.file_saved), Toast.LENGTH_SHORT).show()

            etFileName.text.clear()
            etFileContents.text.clear()

        } catch (e: FileNotFoundException) {
        } catch (e: IOException) {
        } catch (e: Exception) {
        } finally {
            outputStream?.close()
        }
    }

    private fun readTextFile() {
        val stringBuilder = StringBuilder()
        val fileName = etFileName.text.toString().plus(".txt")
        var inputStream: InputStream? = null

        try {
            inputStream = openFileInput(fileName)
            var read = inputStream.read()
            while (read != -1) {
                stringBuilder.append(read.toChar())
                read = inputStream.read()
            }
        } catch (e: Exception) {

        }
        tvFileContents.text = stringBuilder.toString()

    }

    private fun saveImageFile() {
        val fileName = etFileName.text.toString().plus(".jpg")
        val image = getBitmap()
        var fileOutputStream: FileOutputStream? = null

        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
            image!!.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream)
            Toast.makeText(this, getString(R.string.image_saved), Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
        }
    }

    private fun readImageFile() {
        var bitmap: Bitmap? = null
        val fileName = etFileName.text.toString().plus(".jpg")
        var inputStream: InputStream? = null
        try {
            inputStream = openFileInput(fileName)
            bitmap = BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
        }
        ivFile.setImageBitmap(bitmap)

    }

    private fun getBitmap(): Bitmap? {
        var image: Bitmap? = null
        try {
            val inputStream = assets.open("image2.jpg")
            image = BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
        }
        return image
    }

}
