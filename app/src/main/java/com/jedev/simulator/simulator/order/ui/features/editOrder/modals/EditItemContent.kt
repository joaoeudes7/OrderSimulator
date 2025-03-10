package com.jedev.simulator.simulator.order.ui.features.editOrder.modals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jedev.simulator.simulator.order.R
import com.jedev.simulator.simulator.order.domain.models.ItemModel
import com.jedev.simulator.simulator.order.ui.features.editOrder.EditOrderUiActions

@Composable
fun EditItemContent(
    item: ItemModel,
    actions: EditOrderUiActions
) = Column(
    Modifier
        .fillMaxWidth()
        .padding(16.dp)
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = item.description,
        onValueChange = { actions.onChangeDescriptionItem(it) },
        label = { Text(stringResource(R.string.description)) },
        singleLine = true,
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = item.unitPrice.toString(),
        prefix = { Text(stringResource(R.string.prefix_price)) },
        onValueChange = { actions.onChangeUnitPriceItem(it) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Decimal
        ),
        label = { Text(stringResource(R.string.unit_price)) }
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = item.quantity.toString(),
        onValueChange = { },
        leadingIcon = {
            TextButton(
                onClick = { actions.onDecreaseQuantityItem() },
                enabled = item.quantity > 1
            ) {
                Text(stringResource(R.string.minus))
            }
        },
        trailingIcon = {
            TextButton(
                onClick = { actions.onIncreaseQuantityItem() },
            ) {
                Text(stringResource(R.string.add))
            }
        },
        singleLine = true,
        readOnly = true,
        label = { Text(stringResource(R.string.quantity)) }
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { actions.onRemoveItem() }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = stringResource(R.string.delete))
        }

        OutlinedButton(
            modifier = Modifier.weight(1f),
            onClick = { actions.onSaveItem() }
        ) {
            Text(stringResource(R.string.save))
        }
    }
}

@Preview
@Composable
private fun PreviewEditItemContent() = EditItemContent(
    item = ItemModel(1, "Item 1", 2, 10.0),
    actions = EditOrderUiActions()
)