package com.example.imagecrop.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.imagecrop.R
import com.example.imagecrop.utils.Constants
import kotlinx.android.synthetic.main.fragment_image_result.*

/**
 * A simple [Fragment] subclass.
 */
class ImageResultFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val imageResultFragment = ImageResultFragment()
            imageResultFragment.arguments = bundle
            return imageResultFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_result, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setData()

    }

    private fun init() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.cropped_image)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setData() {
        val imagePath = arguments!!.getString(Constants.IMAGE_PATH)
        tvImagePath.text = getString(R.string.image_path).plus(" : ").plus(imagePath)
        ivResult.setImageURI(Uri.parse(imagePath))
    }


}
