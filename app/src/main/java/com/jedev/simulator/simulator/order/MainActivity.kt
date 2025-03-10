package com.jedev.simulator.simulator.order

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.jedev.simulator.simulator.order.ui.theme.SimulatorOrdersTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SimulatorOrdersTheme {
                AppNavHost(
                    startDestination = Destinations.Home
                )
            }
        }
    }
}
