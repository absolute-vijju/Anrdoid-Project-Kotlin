package com.thefit.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thefit.R
import com.thefit.models.WeightHistoryData
import com.thefit.utils.ItemClickListener
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.header.view.tvTitle
import kotlinx.android.synthetic.main.item_weight_history.view.*


class WeightHistoryAdapter(
    private val activity: Activity,
    private val weightHistoryDataList: List<WeightHistoryData>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> R.layout.header
            else -> R.layout.item_weight_history
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.header -> HeaderViewHolder(view)
            else -> WeightHistoryViewHolder(view)
        }

    }

    override fun getItemCount(): Int {
        return weightHistoryDataList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bindData()
            else -> (holder as WeightHistoryViewHolder).bindData(weightHistoryDataList[position - 1])
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData() {
            itemView.ivBack.visibility = View.VISIBLE
            itemView.ivMenu.visibility=View.INVISIBLE

            itemView.ivMenu.setImageResource(R.drawable.ic_action_settings)
            itemView.tvTitle.text = activity.getString(R.string.weight_history).toUpperCase()

            itemView.ivMenu.setOnClickListener(this)
            itemView.ivBack.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            when (p0?.id) {
                R.id.ivMenu -> itemClickListener.onItemClick(this, adapterPosition, R.id.ivMenu)
                R.id.ivBack -> itemClickListener.onItemClick(this, adapterPosition, R.id.ivBack)
            }
        }
    }

    inner class WeightHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(weightHistoryData: WeightHistoryData) {
            itemView.tvDate.text = weightHistoryData.date
            itemView.tvWeight.text = weightHistoryData.weight
        }
    }

}
