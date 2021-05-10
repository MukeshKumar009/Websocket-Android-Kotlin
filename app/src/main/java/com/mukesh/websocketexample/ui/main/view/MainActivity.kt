package com.mukesh.websocketexample.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mukesh.websocketexample.R
import com.mukesh.websocketexample.model.Citydata
import com.mukesh.websocketexample.ui.main.adapter.MainAdapter
import com.mukesh.websocketexample.ui.main.viewmodel.MainViewModel
import com.mukesh.websocketexample.utils.JsonParser
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList

/**
 * Activity to Show Main UI for Air Quality
 * View model will provide us data from websocket class
 * Our observer will update the UI once data is updated
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private var mapCitydata  : HashMap<String, String> = HashMap<String, String> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    //Update UI once data is updated
    private fun setupObserver() {

        mainViewModel.fetchCityData().observe(this, Observer {
            it?.let {
                progressBar.visibility = View.VISIBLE
                Log.d("MainActivity", "onTextMessage: $it")
                var listCityData = JsonParser().parseCityData(it,mapCitydata)
                renderListCity(listCityData)
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        })
    }

    private fun renderListCity(citydata:  ArrayList<Citydata>) {
        adapter.addCityData(citydata)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this).get(MainViewModel::class.java)
    }
}

