package com.aditya.shopliteapp.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.aditya.shopliteapp.data.fake.FakeProductSource
import com.aditya.shopliteapp.domain.model.CartItem
import com.aditya.shopliteapp.domain.model.toPrice
import com.aditya.shopliteapp.ui.theme.ShopLiteAppTheme

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = viewModel()
) {
    val items by viewModel.cart.collectAsStateWithLifecycle()
    CartContent(
        items = items,
        onIncrease = { viewModel.increase(it) },
        onDecrease = { viewModel.decrease(it) },
        onRemove = { viewModel.remove(it) },
        modifier = modifier
    )
}

@Composable
fun CartContent(
    items: List<CartItem>,
    onIncrease: (CartItem) -> Unit,
    onDecrease: (CartItem) -> Unit,
    onRemove: (CartItem) -> Unit,
    modifier: Modifier = Modifier
) {
    if (items.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Your cart is empty")
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Your cart", style = MaterialTheme.typography.headlineSmall)

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items, key = { it.product.id }) { item ->
                    CartItemRow(
                        item = item,
                        onIncrease = { onIncrease(item) },
                        onDecrease = { onDecrease(item) },
                        onRemove = { onRemove(item) }
                    )
                }
            }

            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Total", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = items.sumOf { it.totalPrice }.toPrice(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CartItemRow(
    item: CartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.product.thumbnail,
            contentDescription = item.product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = item.product.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = item.totalPrice.toPrice(), fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextButton(onClick = onDecrease) { Text("−") }
                Text(text = "${item.quantity}")
                TextButton(onClick = onIncrease) { Text("+") }
            }
        }
        IconButton(onClick = onRemove) {
            Icon(Icons.Default.Delete, contentDescription = "Remove ${item.product.title}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartContentPreview() {
    ShopLiteAppTheme {
        CartContent(
            items = listOf(
                CartItem(FakeProductSource.products[2], 2),
                CartItem(FakeProductSource.products[4], 1)
            ),
            onIncrease = {},
            onDecrease = {},
            onRemove = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CartEmptyPreview() {
    ShopLiteAppTheme {
        CartContent(items = emptyList(), onIncrease = {}, onDecrease = {}, onRemove = {})
    }
}
