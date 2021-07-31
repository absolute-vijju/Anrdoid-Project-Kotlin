package com.example.mpandroidchart

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mChart.isDragEnabled = true
        mChart.isDragEnabled = true

        val yValues = arrayListOf<Entry>()
        yValues.add(Entry(0f, 60f))
        yValues.add(Entry(30f, 30f))
        yValues.add(Entry(50f, 90f))
        yValues.add(Entry(70f, 40f))
        yValues.add(Entry(90f, 70f))
        val dataSet = LineDataSet(yValues, "Data Set 1")
        dataSet.color = ContextCompat.getColor(this, R.color.line)

        val lineData = LineData(dataSet)
        lineData.setValueTextColor(Color.BLACK)

        mChart.data = lineData
        mChart.setDrawGridBackground(false)

        mChart.invalidate()

    }
}