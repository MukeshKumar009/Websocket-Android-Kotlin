# Websocket-Android-Kotlin
Get continuous update from websocket Url and show the data on List

This is the project to display Air Quality of different cities.

Websocket library used : com.neovisionaries:nv-websocket-client:2.4
Chart library used : com.github.PhilJay:MPAndroidChart:v3.1.0

Jetpack library used : Lifecycle (ViewModel)
                       Navigation Graph
                       LiveData
                       
App Architecture : Single Activity App with MVVM 

Data Flow : There are two fragemts in this app, 1. To show the List, 2. To show the chart of selected item, View model class will connect with Websocket class for data request from server and Websocket will return a Live data which will be observerd by both fragments. Single ViewModel has been used for both view classes. 
