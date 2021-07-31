package com.example.sharedelementtransitionrecyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val customerData = getData()

        rvCustomers.layoutManager = LinearLayoutManager(activity!!)
        val customerAdapter = CustomerAdapter(activity!!, customerData)
        rvCustomers.adapter = customerAdapter

    }

    private fun getData(): List<Customer> {
        val customer1 = Customer("Vijay", "Koshti", "Male")
        val customer2 = Customer("Hardik", "Koshti", "Male")
        val customer3 = Customer("Komal", "Koshti", "Female")
        val customer4 = Customer("Vraj", "Koshti", "Male")
        val customer5 = Customer("Mehul", "Koshti", "Male")
        val customer6 = Customer("Sumit", "Koshti", "Male")
        val customer7 = Customer("Dharmik", "Modi", "Male")
        val customer8 = Customer("Rohan", "Patel", "Male")
        val customer9 = Customer("Karan", "Soni", "Male")
        val customer10 = Customer("Nirav", "Vasava", "Male")
        return listOf<Customer>(
            customer1,
            customer2,
            customer3,
            customer4,
            customer5,
            customer6,
            customer7,
            customer8,
            customer9,
            customer10
        )

    }
}