package com.bodiesbyrachel.jetpacknavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* val options = navOptions {
             anim {
                 enter = R.anim.slide_in_right
                 exit = R.anim.slide_out_left
                 popEnter = R.anim.slide_in_left
                 popExit = R.anim.slide_out_right
             }
         }*/

        btnNext.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_firstFragment_to_secondFragment,
                null
            )
        )


    }

}
