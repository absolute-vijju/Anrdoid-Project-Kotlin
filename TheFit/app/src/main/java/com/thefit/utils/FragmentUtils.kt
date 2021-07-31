package com.thefit.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentUtils {
    companion object {
        fun addFragment(
            supportFragmentManager: FragmentManager,
            container: Int,
            fragment: Fragment
        ) {
            supportFragmentManager.beginTransaction().add(container, fragment).commit()
        }

        fun addFragmentWithBackStack(
            supportFragmentManager: FragmentManager,
            container: Int,
            fragment: Fragment
        ) {
            supportFragmentManager.beginTransaction().add(container, fragment)
                .addToBackStack(fragment::class.java.simpleName).commit()
        }

        fun replaceFragment(
            supportFragmentManager: FragmentManager,
            container: Int,
            fragment: Fragment
        ) {
            supportFragmentManager.beginTransaction().replace(container, fragment).commit()
        }

        fun replaceFragmentWithBackStack(
            supportFragmentManager: FragmentManager,
            container: Int,
            fragment: Fragment
        ) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(fragment::class.java.simpleName).replace(container, fragment)
                .commit()
        }
    }
}