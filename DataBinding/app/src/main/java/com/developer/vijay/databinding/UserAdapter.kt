package com.developer.vijay.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.vijay.databinding.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var userList = listOf<User>()

    inner class UserViewHolder(val mBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.apply {
            mBinding.user = userList[adapterPosition]
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(userList: ArrayList<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}