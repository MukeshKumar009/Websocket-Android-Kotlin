package com.mukesh.websocketexample.ui.main.view.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mukesh.websocketexample.R
import com.mukesh.websocketexample.model.Citydata
import com.mukesh.websocketexample.ui.main.adapter.MainAdapter
import com.mukesh.websocketexample.utils.JsonParser
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.HashMap


/**
 * Fragment to Show Main UI for Air Quality
 * View model will provide us data from websocket class
 * Our observer will update the UI once data is updated
 */

class MainActivityWebSocketFragment : Fragment() {

    companion object {
        fun newInstance() = MainActivityWebSocketFragment()
    }

    private lateinit var mainViewModel:MainViewModel
    private lateinit var adapter: MainAdapter
    private var mapCitydata  : HashMap<String, String> = HashMap<String, String> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
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

        mainViewModel.fetchCityData().observe(viewLifecycleOwner, Observer {
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
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

}