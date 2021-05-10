package com.mukesh.websocketexample.ui.main.view.ui.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.mukesh.websocketexample.R
import com.mukesh.websocketexample.utils.ChartConfig
import com.mukesh.websocketexample.ui.main.adapter.MainAdapter
import com.mukesh.websocketexample.utils.JsonParser
import kotlinx.android.synthetic.main.fragment_chart.*


/**
 * A simple [Fragment] subclass.
 * Use the [ChartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChartFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var cityName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cityName = it.getString(MainAdapter.CITY_NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val lineChartView = this.lineChartView
        setupViewModel()
        setupObserver(lineChartView)
    }

    private fun setupObserver(lineChartView : LineChart) {

        var listCityData : ArrayList<Float> = arrayListOf()
        mainViewModel.fetchCityData().observe(viewLifecycleOwner, Observer {
            it?.let {
                progressBarChart.visibility = View.VISIBLE
                Log.d("MainActivity", "onTextMessage: $it")
                var aqiFloat = JsonParser().parseChartData(it,cityName)
                //Show chart for last 5 data
                if (listCityData.size<=5) {
                    listCityData.add(aqiFloat)
                }else {
                    progressBarChart.visibility = View.GONE
                    setupChartUI(cityName,lineChartView,listCityData)
                }
            }
        })
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    }

    //Configure UI of chart
    private fun setupChartUI(cityName : String, lineChartView : LineChart, listCityData : ArrayList<Float>){
        lineChartView.data = ChartConfig().setupLineChart(listCityData)
        var desc = Description()
        desc.text = cityName
        desc.textColor = Color.RED
        desc.textSize = 12f
        lineChartView.description = desc
        lineChartView.invalidate()
    }
}