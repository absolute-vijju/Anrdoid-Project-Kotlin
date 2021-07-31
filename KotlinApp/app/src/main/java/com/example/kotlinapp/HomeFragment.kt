package com.example.kotlinapp


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("mydata", "OnCreate View")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("mydata", "OnCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("mydata", "onActivityCreated")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d("mydata", "onAttach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("mydata", "onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("mydata", "onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("mydata", "onDetach")
    }

    override fun onPause() {
        super.onPause()
        Log.d("mydata", "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("mydata", "onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d("mydata", "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("mydata", "onStop")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("mydata", "onViewCreated")
    }
}
