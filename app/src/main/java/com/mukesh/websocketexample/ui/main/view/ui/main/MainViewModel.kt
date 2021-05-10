package com.mukesh.websocketexample.ui.main.view.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mukesh.websocketexample.api.WebsocketClass

/**
 * Common view model for Both Activity UI
 */
class MainViewModel : ViewModel() {

    val websocket = WebsocketClass()
    //Get data from Wesocket class
    fun fetchCityData(): LiveData<String> {
        return websocket.getCityData()
    }

    override fun onCleared() {
        super.onCleared()
        //Close connection
        websocket.closeWebsocketConnection()
    }
}