package com.thefit.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.thefit.R
import com.thefit.utils.ItemClickListener
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.header.view.tvTitle
import kotlinx.android.synthetic.main.item_current_measurement.view.*
import kotlinx.android.synthetic.main.item_profile.view.*
import kotlinx.android.synthetic.main.item_weight.view.*
import kotlinx.android.synthetic.main.item_weight.view.tvCurrentWeightCount


class InfoAdapter(
    private val activity: Activity, private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> R.layout.header
            1 -> R.layout.item_profile
            2 -> R.layout.item_current_measurement
            else -> R.layout.item_weight
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(activity).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.header -> HeaderViewHolder(view)
            R.layout.item_profile -> ProfileViewHolder(view)
            R.layout.item_current_measurement -> CurrentMeasurementViewHolder(view)
            else -> WeightViewHolder(view)
        }

    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bindData()
            is ProfileViewHolder -> holder.bindData()
            is CurrentMeasurementViewHolder -> holder.bindData()
            else -> (holder as WeightViewHolder).bindData()
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData() {
            itemView.ivMenu.setImageResource(R.drawable.ic_action_settings)
            itemView.tvTitle.text = activity.getString(R.string.me).toUpperCase()
            itemView.ivMenu.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.ivMenu -> itemClickListener.onItemClick(this, adapterPosition, R.id.ivMenu)
            }
        }
    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData() {
            itemView.vProfileDivider.visibility = View.VISIBLE
            itemView.tvUsername.text = activity.getString(R.string.username)
        }
    }

    inner class CurrentMeasurementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData() {
            itemView.vCurrentMeasurementDivider.visibility = View.VISIBLE
            itemView.tvStartingWeightCount.text = "62.0kg"
            itemView.tvCurrentWeightCount.text = "-kg"
            itemView.tvBodyFatCount.text = "2.0%"
        }
    }

    inner class WeightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        fun bindData() {
            itemView.tvCurrentWeightCount.text = "60.0kg"

            setGraphData()

            itemView.tvViewHistory.setOnClickListener(this)
        }

        private fun getEntries(): ArrayList<Entry> {
            val entries = arrayListOf<Entry>()
            /*for (index in 0..30) {
                entries.add(Entry(index.toFloat(), Random.nextInt(120).toFloat()))
            }*/
            entries.add(Entry(10f, 20f))
            entries.add(Entry(50f, 30f))
            entries.add(Entry(80f, 10f))
            entries.add(Entry(100f, 120f))
            entries.add(Entry(100f, 80f))
            entries.add(Entry(100f, 30f))
            return entries
        }

        private fun setGraphData() {
            val graphData = getEntries()
            val yAxis = arrayOf("60", "80", "100", "120")

            itemView.vGraph.setScaleEnabled(false)
            itemView.vGraph.setPinchZoom(false)
            itemView.vGraph.setTouchEnabled(false)
            itemView.vGraph.setDrawGridBackground(true)
            itemView.vGraph.setGridBackgroundColor(ContextCompat.getColor(activity, R.color.bg_workout_history))
            itemView.vGraph.isDoubleTapToZoomEnabled = false
            itemView.vGraph.description.isEnabled = false   // bottom right corner description
            itemView.vGraph.legend.isEnabled = false  // label = legend (bottom left corner)

            val lineDataSet = LineDataSet(graphData, null)
//            lineDataSet.mode=LineDataSet.Mode.HORIZONTAL_BEZIER
            lineDataSet.color = ContextCompat.getColor(activity, android.R.color.transparent) // line color
            lineDataSet.setDrawCircles(false)   // to disable circle indicator
            lineDataSet.setDrawFilled(true)
            lineDataSet.fillColor = ContextCompat.getColor(activity, R.color.chart) // fill color of chart
            val lineData = LineData(lineDataSet)
            lineData.setDrawValues(false)   // to disable text values
            itemView.vGraph.data = lineData

            val leftYAxis = itemView.vGraph.axisLeft
            leftYAxis.gridColor = ContextCompat.getColor(activity, R.color.chart_grid_line)
            leftYAxis.typeface = ResourcesCompat.getFont(activity, R.font.montserrat_regular)
            leftYAxis.axisMinimum = 0f
            leftYAxis.axisMaximum = 120f
            leftYAxis.textColor=ContextCompat.getColor(activity, R.color.y_axis_label)
            leftYAxis.setLabelCount(4, true)
            leftYAxis.setDrawAxisLine(false)


            itemView.vGraph.axisRight.isEnabled = false
            itemView.vGraph.xAxis.isEnabled = false

            itemView.vGraph.animateXY(1500, 1500)
            itemView.vGraph.invalidate()

        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.tvViewHistory -> itemClickListener.onItemClick(
                    this,
                    adapterPosition,
                    R.id.tvViewHistory
                )
            }
        }
    }

}
