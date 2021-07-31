package com.example.userregistrationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.example.userregistrationapp.fragments.RegistrationFragment
import com.example.userregistrationapp.fragments.UsersFragment
import com.example.userregistrationapp.utils.FragmentUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FragmentUtils.replaceFragmentWithoutBackStack(
            supportFragmentManager,
            R.id.flMainActivityContainer, UsersFragment.newInstance(null)
        )

    }

}