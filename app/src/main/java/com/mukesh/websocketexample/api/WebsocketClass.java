package com.mukesh.websocketexample.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;

/**
 * Class to handle websocekt connection and get data
 */
public class WebsocketClass {

    //Livedata to update the value to view model
    public MutableLiveData<String> cityData;
    WebSocket ws = null;

    public LiveData<String> getCityData() {
        if (cityData == null) {
            cityData = new MutableLiveData<>();
            initWebsocket();
        }
        return cityData;
    }

    //Initiate websocket connection and get updated data
    public void initWebsocket() {

        WebSocketFactory factory = new WebSocketFactory().setConnectionTimeout(5000);
        // Create a WebSocket. The timeout value set above is used.
        try {
            ws = factory.createSocket("ws://city-ws.herokuapp.com/");

            ws.addListener(new WebSocketAdapter() {
                @Override
                public void onTextMessage(WebSocket websocket, String message) throws Exception {
                    Log.d("TAG", "onTextMessage: " + message);
                    cityData.postValue(message);
                }
            });

            ws.connectAsynchronously();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   //Close connection
    public void closeWebsocketConnection(){
        ws.disconnect();
    }
}


