package com.example.userregistrationapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userregistrationapp.R
import com.example.userregistrationapp.room.UserEntity
import com.example.userregistrationapp.utils.ItemClickListener
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(
    private val context: Context,
    private var userList: List<UserEntity>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(userEntity: UserEntity?) {
            userEntity?.let {
                itemView.tvFullname.text = userEntity.FullName
                itemView.tvAddress.text = userEntity.Address
                itemView.tvEmail.text = userEntity.Email
                itemView.tvCity.text = userEntity.City
                itemView.tvState.text = userEntity.State
                itemView.tvCountry.text = userEntity.Country
                itemView.tvZipCode.text = userEntity.ZipCode
                itemView.tvPhoneNumber.text = userEntity.PhoneNumber
                itemView.tvMobile.text = userEntity.Mobile

                itemView.setOnClickListener(View.OnClickListener {
                    itemClickListener.onItemClick(this, adapterPosition, null)
                })

                itemView.ivDelete.setOnClickListener(View.OnClickListener {
                    itemClickListener.onItemClick(this, adapterPosition, R.id.ivDelete)
                })

            }
        }
    }

    fun updateAdapter(userList: List<UserEntity>) {
        this.userList = userList
        notifyDataSetChanged()
    }

}