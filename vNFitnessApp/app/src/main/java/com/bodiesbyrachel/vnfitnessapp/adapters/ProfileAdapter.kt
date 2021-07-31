package com.bodiesbyrachel.vnfitnessapp.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.models.ProfileData
import com.bodiesbyrachel.vnfitnessapp.utils.ItemClickListener
import kotlinx.android.synthetic.main.item_profile.view.*
import kotlinx.android.synthetic.main.item_profile_header.view.*

class ProfileAdapter(
    private val activity: Activity,
    private val profileData: List<ProfileData>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return R.layout.item_profile_header
        return R.layout.item_profile
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        if (viewType == R.layout.item_profile_header)
            return ProfileHeaderViewHolder(view)
        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return profileData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProfileHeaderViewHolder)
            holder.bindData(position)
        else
            (holder as ProfileViewHolder).bindData(profileData[position])
    }

    inner class ProfileHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData(position: Int) {
            itemView.tvUsername.text = profileData[position].title

            itemView.ivBack.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.ivBack -> {
                    itemClickListener.onItemClick(this, adapterPosition, R.id.ivBack)
                }
            }
        }
    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(profileData: ProfileData) {
            if (adapterPosition != 1)
                itemView.ivArrow.visibility = View.VISIBLE

            if (profileData.title!!.equals(activity.getString(R.string.email_address), true))
                itemView.ivIcon.setImageResource(R.drawable.ic_action_user)
            else if (profileData.title.equals(activity.getString(R.string.sign_out), true))
                itemView.ivIcon.setImageResource(R.drawable.ic_action_exit_to_app)
            else
                itemView.ivIcon.setImageResource(R.drawable.ic_action_help_outline)


            itemView.tvTitle.text = profileData.title
            itemView.tvDesc.text = profileData.desc
        }
    }
}