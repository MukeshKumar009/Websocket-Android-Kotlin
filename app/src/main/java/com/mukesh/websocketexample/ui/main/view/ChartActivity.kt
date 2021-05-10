package com.mukesh.websocketexample.ui.main.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.mukesh.websocketexample.R
import com.mukesh.websocketexample.ui.main.ChartConfig
import com.mukesh.websocketexample.ui.main.adapter.MainAdapter.Companion.CITY_NAME
import com.mukesh.websocketexample.ui.main.viewmodel.MainViewModel
import com.mukesh.websocketexample.utils.JsonParser
import kotlinx.android.synthetic.main.activity_chart.*

/**
 * Activity to show chart
 * This class should have seperate Url for getting data for selected city itself.
 * But if that is not available then I'm calling same Url with all city data and selecting the
 * selected city last 5 data to draw line chart
 * Because timestamp is not coming with data it's hard to manually keep track of all time.
 * Ideally, server should send all data with particular time diffeence eg . 30 second
 * Chart UI can be improved.
 */
class ChartActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var cityName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        //get city name from intent
        cityName = intent.getStringExtra(CITY_NAME)!!

        val lineChartView = this.lineChartView
        setupViewModel()
        setupObserver(lineChartView)
    }

    private fun setupObserver(lineChartView : LineChart) {

        var listCityData : ArrayList<Float> = arrayListOf()
        mainViewModel.fetchCityData().observe(this, Observer {
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
        mainViewModel = ViewModelProviders.of(
            this).get(MainViewModel::class.java)
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