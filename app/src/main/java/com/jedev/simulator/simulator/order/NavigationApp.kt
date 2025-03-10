package com.jedev.simulator.simulator.order

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jedev.simulator.simulator.order.Destinations.Home
import com.jedev.simulator.simulator.order.ui.features.editOrder.EditOrderScreen
import com.jedev.simulator.simulator.order.ui.features.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
sealed class Destinations {
    @Serializable
    object Home : Destinations()

    @Serializable
    class EditOrder(val orderId: Int?) : Destinations()
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: Destinations
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Home> {
            HomeScreen(
                onAddOrder = { navController.navigate(Destinations.EditOrder(null)) },
                onSeeDetailsOrder = { navController.navigate(Destinations.EditOrder(it)) }
            )
        }
        composable<Destinations.EditOrder> { backStackEntry ->
            val order: Destinations.EditOrder = backStackEntry.toRoute()

            EditOrderScreen(
                orderId = order.orderId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}