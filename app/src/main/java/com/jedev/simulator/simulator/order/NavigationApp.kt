package com.jedev.simulator.simulator.order

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jedev.simulator.simulator.order.Destinations.Home
import com.jedev.simulator.simulator.order.ui.features.editOrder.EditOrderScreen
import com.jedev.simulator.simulator.order.ui.features.home.HomeScreen
import kotlinx.serialization.Serializable
import java.util.concurrent.TimeUnit

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
    val activity = LocalActivity.current

    var lastExitRequest by remember {
        mutableLongStateOf(0L)
    }

    fun onBack() {
        navController.popBackStack()
    }

    BackHandler {
        when {
            navController.previousBackStackEntry == null -> {
                val now = System.currentTimeMillis()
                val timeoutExit = now - lastExitRequest < TimeUnit.SECONDS.toMillis(5)

                lastExitRequest = now

                var msgToast = activity?.getString(R.string.press_back_again)

                if (timeoutExit) {
                    msgToast = activity?.getString(R.string.safe_exit_finished)
                    activity?.finish()
                }

                Toast.makeText(activity, msgToast, Toast.LENGTH_SHORT).show()
            }
            else -> onBack()
        }
    }

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