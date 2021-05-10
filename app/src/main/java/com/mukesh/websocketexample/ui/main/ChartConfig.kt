package com.mukesh.websocketexample.ui.main

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

/**
 * Class for chart configuration
 */
class ChartConfig {

    fun setupLineChart(listCityData : ArrayList<Float>) : LineData{
        //Create dataset
        var i = 0
        val entries = ArrayList<Entry>()
        for (data in listCityData) {
            entries.add(Entry(i++.toFloat(), data))
        }
        val lineDataSet = LineDataSet(entries, "AQI Level")
        val lineData = LineData(lineDataSet)

        return lineData
    }
}