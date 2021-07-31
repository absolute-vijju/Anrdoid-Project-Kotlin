package com.example.userregistrationapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentUtils {
    companion object {
        fun replaceFragmentWithBackStack(
            fragmentManager: FragmentManager,
            container: Int,
            fragment: Fragment
        ) {
            fragmentManager.beginTransaction().replace(container, fragment)
                .addToBackStack(fragment::class.java.simpleName).commit()
        }

        fun replaceFragmentWithoutBackStack(
            fragmentManager: FragmentManager,
            container: Int,
            fragment: Fragment
        ) {
            fragmentManager.beginTransaction().replace(container, fragment).commit()
        }
    }
}