package com.jedev.simulator.simulator.order.ui.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jedev.simulator.simulator.order.R
import com.jedev.simulator.simulator.order.domain.models.ItemModel
import com.jedev.simulator.simulator.order.domain.models.OrderModel
import com.jedev.simulator.simulator.order.ui.components.OrderListItem
import com.jedev.simulator.simulator.order.ui.components.SVTopBar
import com.jedev.simulator.simulator.order.ui.theme.SimulatorOrdersTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    vm: HomeViewModel = koinViewModel(),
    onAddOrder: () -> Unit,
    onSeeDetailsOrder: (Int) -> Unit
) {
    LaunchedEffect(Unit) {
        vm.fetchOrders()
    }

    HomeScreenContent(
        uiState = vm.uiState,
        uiActions = HomeUiActions(
            onOrderClick = { onSeeDetailsOrder(it) },
            onAddOrder = { onAddOrder() }
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    uiActions: HomeUiActions
) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
        SVTopBar(title = stringResource(R.string.title_app))
    },
    floatingActionButton = {
        FloatingActionButton(onClick = { uiActions.onAddOrder() }) {
            Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_item))
        }
    }
) { innerPadding ->
    LazyColumn(modifier = Modifier.padding(innerPadding)) {
        items(uiState.orders, key = { it.id }) { it ->
            OrderListItem(order = it) { uiActions.onOrderClick(it.id) }
        }

        item {
            if (uiState.orders.isEmpty()) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_item)
                    )

                    Text(stringResource(R.string.no_items_found))
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewScreen() = SimulatorOrdersTheme {
    HomeScreenContent(
        uiState = HomeUiState().apply {
            orders = listOf(
                OrderModel(
                    items = mutableListOf(
                        ItemModel(1, "Item 1", 2, 10.0),
                        ItemModel(2, "Item 2", 1, 20.0)
                    )
                )
            )
        },
        uiActions = HomeUiActions()
    )
}