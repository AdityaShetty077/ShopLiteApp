package com.aditya.shopliteapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aditya.shopliteapp.data.fake.FakeProductSource
import com.aditya.shopliteapp.domain.model.Category
import com.aditya.shopliteapp.domain.model.SortOrder
import com.aditya.shopliteapp.ui.theme.ShopLiteAppTheme

@Composable
fun ProductSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Search products") },
        singleLine = true,
        modifier = modifier
    )
}

@Composable
fun CategoryChips(
    categories: List<Category>,
    selected: String?,
    onSelect: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            FilterChip(
                selected = selected == null,
                onClick = { onSelect(null) },
                label = { Text("All") }
            )
        }
        items(categories, key = { it.slug }) { category ->
            FilterChip(
                selected = category.slug == selected,
                onClick = { onSelect(category.slug) },
                label = { Text(category.name) }
            )
        }
    }
}

@Composable
fun SortMenu(
    selected: SortOrder,
    onSelect: (SortOrder) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        TextButton(onClick = { expanded = true }) {
            Text("Sort: ${selected.label}")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            SortOrder.entries.forEach { order ->
                DropdownMenuItem(
                    text = { Text(order.label) },
                    onClick = {
                        onSelect(order)
                        expanded = false
                    }
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProductSearchFieldPreview() {
    ShopLiteAppTheme {
        ProductSearchField(query = "iph", onQueryChange = {})
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryChipsPreview() {
    ShopLiteAppTheme {
        CategoryChips(
            categories = FakeProductSource.categories,
            selected = "laptops",
            onSelect = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SortMenuPreview() {
    ShopLiteAppTheme {
        SortMenu(selected = SortOrder.PRICE_LOW_TO_HIGH, onSelect = {})
    }
}

