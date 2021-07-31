package com.example.imagecrop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.imagecrop.R
import com.example.imagecrop.fragments.ExoFragment
import com.example.imagecrop.fragments.VideoViewFragment
import com.example.imagecrop.utils.Constants

class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val intentData =
            intent.getSerializableExtra(Constants.LIBRARY_TYPE) as Constants.LibraryType

        checkType(intentData)

    }

    private fun checkType(libraryType: Constants.LibraryType) {
        when (libraryType) {
            Constants.LibraryType.VideoView -> addFragment(VideoViewFragment.newInstance(null))
            Constants.LibraryType.ExoPlayer -> addFragment(ExoFragment.newInstance(null))
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.flVideoContainer, fragment).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStack()
        else
            finish()
        return super.onSupportNavigateUp()
    }


}
