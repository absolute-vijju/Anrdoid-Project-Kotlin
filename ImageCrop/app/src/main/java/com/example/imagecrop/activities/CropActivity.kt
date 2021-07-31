package com.example.imagecrop.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import com.example.imagecrop.R
import com.example.imagecrop.fragments.*
import com.example.imagecrop.utils.Constants
import kotlinx.android.synthetic.main.fragment_crop_me.*

class CropActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop)

        val intentData =
            intent.getSerializableExtra(Constants.LIBRARY_TYPE) as Constants.LibraryType

        checkType(intentData)

    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.flCropContainer, fragment).commit()
    }

    fun replaceFragmentWithBackStack(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.flCropContainer, fragment)
            .addToBackStack(fragment::class.java.simpleName).commit()
    }

    private fun checkType(libraryType: Constants.LibraryType) {
        when (libraryType) {
            Constants.LibraryType.Scissors -> addFragment(ScissorsFragment.newInstance(null))
            Constants.LibraryType.CropMe -> addFragment(CropMeFragment.newInstance(null))
            Constants.LibraryType.CropIwa -> addFragment(CropIwaFragment.newInstance(null))
            Constants.LibraryType.SimpleCropView -> addFragment(
                SimpleCropViewFragment.newInstance(
                    null
                )
            )
            Constants.LibraryType.Android_Image_Cropper -> addFragment(
                AndroidImageCropperFragment.newInstance(
                    null
                )
            )
            Constants.LibraryType.Cropper_NoCropper -> addFragment(
                CropperNoCropperFragment.newInstance(
                    null
                )
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStack()
        else
            finish()
        return super.onSupportNavigateUp()
    }

}
