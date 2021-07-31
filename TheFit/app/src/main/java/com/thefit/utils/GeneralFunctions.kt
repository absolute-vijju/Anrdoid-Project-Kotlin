package com.thefit.utils

import android.app.Activity
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class GeneralFunctions {
    companion object {
        fun loadJSONFromAsset(activity: Activity, fileNameWithExtension: String): String? {
            val json: String?
            try {
                val inputStream: InputStream = activity.assets.open(fileNameWithExtension)
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, Charset.forName("UTF-8"))
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }
            return json
        }
    }
}