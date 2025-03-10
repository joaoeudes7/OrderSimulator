package com.jedev.simulator.simulator.order.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jedev.simulator.simulator.order.domain.models.ItemModel
import com.jedev.simulator.simulator.order.domain.models.OrderModel

@Composable
fun OrderListItem(
    order: OrderModel,
    onClick: () -> Unit
) = Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp, vertical = 6.dp)
        .clickable { onClick() }
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "#${order.id}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )

            Text(
                text = order.dateFormatted,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color.Gray)

        order.items.forEach { item ->
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = item.description,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "${item.quantity} x ${item.unitPriceFormatted}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                VerticalDivider(
                    modifier = Modifier
                        .height(16.dp)
                        .padding(horizontal = 8.dp),
                    color = Color.Gray
                )

                Text(
                    text = item.totalPriceFormatted,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                )
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color.Gray)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                text = "Total: ${(order.totalFormatted)}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        }
    }
}


@Preview
@Composable
private fun PreviewOrderListItem() = OrderListItem(
    order = OrderModel(
        items = mutableListOf(
            ItemModel(1, "Item 1", 3, 10.0),
            ItemModel(2, "Item 2", 1, 20.0)
        )
    ),
    onClick = {}
)