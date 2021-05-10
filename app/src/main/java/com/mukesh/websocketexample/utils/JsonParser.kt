package com.mukesh.websocketexample.utils

import android.util.Log
import com.mindorks.framework.mvvm.utils.Utility
import com.mukesh.websocketexample.model.Citydata
import org.json.JSONArray
import java.util.HashMap

/**
 * Handle all the Json parsing here
 */
class JsonParser {

    //Parse all city data
    fun parseCityData(jsonStr : String, mapCitydata: HashMap<String, String>) : ArrayList<Citydata>{
        var listCityData : ArrayList<Citydata> = arrayListOf()
        val jsonArray = JSONArray(jsonStr)
        for (i in 0 until jsonArray.length()) {
            var jsonObject = jsonArray.getJSONObject(i)
            var city = jsonObject.optString("city") as String
            var aqi = jsonObject.optString("aqi") as String
            mapCitydata.put(city,aqi)
            Log.d("TAG", "MainActivity Json : $city, $aqi")
        }
        for ((key, value) in mapCitydata) {
            println("$key = $value")
            listCityData.add(Citydata(key,value))
        }

        return listCityData
    }

    //Parse data for particular city
    fun parseChartData(jsonStr : String, cityName: String) : Float{
        var aqiFloat : Float = 0.0F
        val jsonArray = JSONArray(jsonStr)
        for (i in 0 until jsonArray.length()) {
            var jsonObject = jsonArray.getJSONObject(i)
            var city = jsonObject.optString("city") as String
            if (cityName.equals(cityName)) {
                val aqi = jsonObject.optString("aqi") as String
                aqiFloat = Utility().stringToDecimal(aqi).toFloat()
                Log.d("TAG", "City Data: $city, $aqiFloat")
            }
        }

        return aqiFloat
    }
}