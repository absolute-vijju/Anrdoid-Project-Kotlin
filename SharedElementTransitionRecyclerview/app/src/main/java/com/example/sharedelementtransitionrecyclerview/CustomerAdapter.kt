package com.example.sharedelementtransitionrecyclerview

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.util.Pair.create
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_customer.view.*

class CustomerAdapter(private val activity: Activity, private val customerData: List<Customer>) :
    RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        return CustomerViewHolder(
            LayoutInflater.from(activity).inflate(R.layout.item_customer, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return customerData.size
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        holder.bindData(position)

        holder.itemView.setOnClickListener {
            val selectedCustomer = customerData[position]
            val firstTransitionName = ViewCompat.getTransitionName(holder.itemView.tvFirstName)
            val secondTransitionName = ViewCompat.getTransitionName(holder.itemView.tvLastName)
            val thirdTransitionName = ViewCompat.getTransitionName(holder.itemView.tvGender)
            val transitionArray =
                arrayListOf(firstTransitionName, secondTransitionName, thirdTransitionName)
            val intent = Intent(activity, DisplayActivity::class.java)
            intent.putExtra("CustomerData", Gson().toJson(selectedCustomer))
            intent.putExtra("TransitionArray", Gson().toJson(transitionArray))

            val activityOption = ActivityOptions.makeSceneTransitionAnimation(
                activity,
                create(holder.itemView.tvFirstName, firstTransitionName!!),
                create(holder.itemView.tvLastName, secondTransitionName!!),
                create(holder.itemView.tvGender, thirdTransitionName!!)
            )
            activity.startActivity(intent, activityOption.toBundle())

        }

    }

    inner class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int) {
            itemView.tvFirstName.text = customerData[position].firstName
            itemView.tvLastName.text = customerData[position].lastName
            itemView.tvGender.text = customerData[position].gender

            ViewCompat.setTransitionName(itemView.tvFirstName, customerData[position].firstName)
            ViewCompat.setTransitionName(itemView.tvLastName, customerData[position].lastName)
            ViewCompat.setTransitionName(itemView.tvGender, customerData[position].gender)
        }
    }
}