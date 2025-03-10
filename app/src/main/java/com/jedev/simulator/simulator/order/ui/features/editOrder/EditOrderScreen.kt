package com.jedev.simulator.simulator.order.ui.features.editOrder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jedev.simulator.simulator.order.R
import com.jedev.simulator.simulator.order.ui.components.SVTopBar
import com.jedev.simulator.simulator.order.ui.components.UnitItemOrderList
import com.jedev.simulator.simulator.order.ui.features.editOrder.modals.EditItemContent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditOrderScreen(
    orderId: Int?,
    vm: EditOrderScreenViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(orderId) {
        if (orderId != null) {
            vm.getOrderById(orderId)
        }
    }

    LaunchedEffect(Unit) {
        vm.events.collect {
            when (it) {
                EditOrderEvents.OnHiddenModal -> sheetState.hide()
                EditOrderEvents.OnSavedOrder ->  onBack()
                else -> {}
            }
        }
    }

    EditOrderScreenContent(
        uiState = vm.uiState,
        actions = EditOrderUiActions(
            onRemoveItem = { vm.onRemoveCurrentItem() },
            onChangeClientName = { vm.onChangeClientName(it) },
            onSelectItem = { vm.onSelectItem(it) },
            onChangeUnitPriceItem = { vm.onChangeUnitPrice(it) },
            onChangeDescriptionItem = { vm.onChangeDescriptionItem(it) },
            onDecreaseQuantityItem = { vm.onDecreaseQuantity() },
            onIncreaseQuantityItem = { vm.onIncreaseQuantity() },
            onSaveOrder = { vm.onSaveOrder() },
            onAddItem = { vm.onAddItem() },
            onSaveItem = { vm.onSaveItem() },
        ),
        sheetState = sheetState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditOrderScreenContent(
    uiState: EditOrderUiState,
    actions: EditOrderUiActions,
    sheetState: SheetState
) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = { SVTopBar(title = uiState.title) },
    floatingActionButton = {
        FloatingActionButton(onClick = { actions.onSaveOrder() }) {
            Icon(Icons.Default.Check, contentDescription = stringResource(R.string.save))
        }
    }
) { padding ->
    uiState.currentItemModelSelected?.let {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { actions.onSaveItem() },
        ) {
            EditItemContent(
                item = it,
                actions = actions
            )
        }
    }

    LazyColumn(Modifier
        .padding(padding)
        .padding(horizontal = 16.dp)) {
        item {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.order.clientName,
                onValueChange = { actions.onChangeClientName(it) },
                label = { Text(stringResource(R.string.name_client)) }
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.items),
                    style = MaterialTheme.typography.titleLarge
                )

                IconButton(onClick = { actions.onAddItem() }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_item)
                    )
                }
            }
        }

        items(uiState.order.items) { item ->
            UnitItemOrderList(item) {
                actions.onSelectItem(item)
            }
        }
    }
}