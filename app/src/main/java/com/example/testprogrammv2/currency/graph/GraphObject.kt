package com.example.testprogrammv2.currency.graph

import com.example.testprogrammv2.R
import com.example.testprogrammv2.currency.`object`.xml.RecordV
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter


class GraphObject (private var lc:LineChart, data: ArrayList<RecordV>) {

    private lateinit var lineChart: LineChart
    private var recordList = data

    fun initiate () : LineChart {
        lineChart = lc
        performXAxis()

        lineChart.animateX(2000, Easing.EaseInSine)
        lineChart.description.isEnabled = false
        lineChart.data = getLinedata()

        return lineChart
    }

    private fun performXAxis()
    {
        lineChart.axisLeft.setDrawGridLines(false)
        lineChart.xAxis.setDrawGridLines(false)
        lineChart.axisRight.setDrawGridLines(false)

        lc.axisLeft.isEnabled = false

        val per_xAxis: XAxis = lineChart.xAxis
        per_xAxis.position = XAxis.XAxisPosition.BOTTOM
        per_xAxis.valueFormatter = MyAxisFormatter()
        per_xAxis.setDrawLabels(true)
        per_xAxis.granularity = 1f
    }

    private fun getLinedata(): LineData
    {
        val entries: ArrayList<Entry> = ArrayList()


        for (i in recordList.indices) {
            val score = recordList[i]
            entries.add(Entry(i.toFloat(), score.Value.toFloat()))
        }

        val lineDataSet = LineDataSet(entries, "")

        lineDataSet.color = R.color.black
        lineDataSet.circleRadius = 0f
        lineDataSet.setDrawFilled(true)
        lineDataSet.fillAlpha = 150
        lineDataSet.lineWidth = 2f
        lineDataSet.valueTextSize = 13f
        lineDataSet.setDrawValues(false)
        return LineData(lineDataSet)
    }


    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
            return if (index < recordList.size) {
                recordList[index].Date
            } else {
                ""
            }
        }
    }

}