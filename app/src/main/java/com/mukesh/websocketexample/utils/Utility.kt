package com.mindorks.framework.mvvm.utils

import android.graphics.Color

/**
 * Common functions to be used from anywhere in the app
 */
class Utility {

    fun getColorCodes(str : String) : Int{
        var color = "#757575"
        var aqiDouble = str.toDouble()

       if(aqiDouble >=0 && aqiDouble<=50){
           color = "#2e7d32"
       }else  if(aqiDouble >=51 && aqiDouble<=100){
           color = "#00e676"
       }else  if(aqiDouble >=101 && aqiDouble<=200){
           color = "#c7a500"
       }else  if(aqiDouble >=201 && aqiDouble<=300){
           color = "#ef6c00"
       }else  if(aqiDouble >=301 && aqiDouble<=400){
           color = "#f50057"
       } else if(aqiDouble >=401 && aqiDouble<=500){
           color = "#d50000"
       }
        val colorInt = Color.parseColor(color)
        return colorInt
    }

    fun stringToDecimal(str : String): String {
        var floatValue : Float? = str.toFloat()
        var valueDecimal= "%.2f".format(floatValue)

        return valueDecimal
    }
}