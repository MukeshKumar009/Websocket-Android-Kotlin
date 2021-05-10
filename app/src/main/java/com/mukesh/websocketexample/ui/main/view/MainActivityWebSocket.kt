package com.mukesh.websocketexample.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.mukesh.websocketexample.R
import com.mukesh.websocketexample.ui.main.view.ui.main.MainActivityWebSocketFragment

/**
 * Host Activity to handle all the fragments
 */
class MainActivityWebSocket : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_web_socket_activity)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }
}