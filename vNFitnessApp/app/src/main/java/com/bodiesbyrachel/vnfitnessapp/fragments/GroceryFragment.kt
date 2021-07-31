package com.bodiesbyrachel.vnfitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.activities.DashboardActivity
import com.bodiesbyrachel.vnfitnessapp.adapters.GroceryAdapter
import com.bodiesbyrachel.vnfitnessapp.models.GroceryResponse
import com.bodiesbyrachel.vnfitnessapp.utils.ItemClickListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_grocery.*
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

/**
 * A simple [Fragment] subclass.
 */
class GroceryFragment : Fragment(), ItemClickListener {

    companion object {
        fun newInstance(bundle: Bundle?): Fragment {
            val groceryFragment = GroceryFragment()
            groceryFragment.arguments = bundle
            return groceryFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grocery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groceryResponse = Gson().fromJson(loadJSONFromAsset(), GroceryResponse::class.java)
        val groceryList = arrayListOf<Any>()
        for (groceryListIndex in groceryResponse.groceryList!!.indices) {
            val obj = groceryResponse.groceryList[groceryListIndex]
            groceryList.add(obj.name!!)
            if (!obj.items.isNullOrEmpty())
                groceryList.addAll(obj.items)
        }
        setAdapter(groceryList)

    }

    private fun setAdapter(groceryList: List<Any>) {
        rvGroceryList.layoutManager = LinearLayoutManager(activity!!)
        val groceryAdapter = GroceryAdapter(activity!!, groceryList, this)
        rvGroceryList.adapter = groceryAdapter
    }

    private fun loadJSONFromAsset(): String? {
        var json: String? = null
        var inputStream: InputStream? = null
        try {

            inputStream = activity!!.assets.open("grocery_list.json")

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

    override fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?) {
        when (viewId) {
            R.id.ivBack -> (activity as DashboardActivity).onBackPressed()
        }
    }

}
