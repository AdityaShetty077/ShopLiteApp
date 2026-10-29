package com.aditya.shopliteapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aditya.shopliteapp.data.fake.FakeProductSource
import com.aditya.shopliteapp.domain.model.Product
import com.aditya.shopliteapp.domain.model.SortOrder
import com.aditya.shopliteapp.ui.components.ProductCard
import com.aditya.shopliteapp.ui.theme.ShopLiteAppTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onProductClick: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel(),

    ) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeContent(
        uiState = uiState, onRetry = { viewModel.loadProducts() }, modifier = modifier,
        onQueryChange = { viewModel.onQueryChange(it) },
        onCategorySelected = { viewModel.onCategorySelected(it) },
        onSortSelected = { viewModel.onSortSelected(it) },
        onProductClick = onProductClick
    )
}

@Composable
fun HomeContent(
    uiState: HomeUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit,
    onCategorySelected: (String?) -> Unit,
    onSortSelected: (SortOrder) -> Unit,
    onProductClick: (Int) -> Unit
) {
    when {
        uiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        uiState.errorMessage != null -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = uiState.errorMessage)
                Button(onClick = onRetry) {
                    Text("Retry")
                }
            }
        }

        else -> {
            Column(modifier = modifier.fillMaxSize()) {
                ProductSearchField(
                    query = uiState.query,
                    onQueryChange = onQueryChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
                CategoryChips(
                    categories = uiState.categories,
                    selected = uiState.selectedCategory,
                    onSelect = onCategorySelected,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                SortMenu(
                    selected = uiState.sortOrder,
                    onSelect = onSortSelected
                )
                if (uiState.visibleProducts.isEmpty()) {
                    Text(
                        text = "No products found",
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    ProductGrid(
                        products = uiState.visibleProducts,
                        onProductClick = onProductClick,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}


@Composable
fun ProductGrid(
    products: List<Product>,
    onProductClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products, key = { it.id }) { product ->
            ProductCard(
                product = product,
                modifier = Modifier.clickable { onProductClick(product.id) })
        }
    }
}


@Preview(name = "Loading", showBackground = true)
@Composable
fun HomeLoadingPreview() {
    ShopLiteAppTheme {
        HomeContent(
            uiState = HomeUiState(isLoading = true), onRetry = {}, onQueryChange = {},
            onCategorySelected = {},
            onSortSelected = {}, onProductClick = {})
    }
}

@Preview(name = "Error", showBackground = true)
@Composable
fun HomeErrorPreview() {
    ShopLiteAppTheme {
        HomeContent(
            uiState = HomeUiState(errorMessage = "No internet connection"),
            onRetry = {},
            onQueryChange = {},
            onCategorySelected = {},
            onSortSelected = {},
            onProductClick = {})
    }
}

@Preview(name = "Success", showBackground = true)
@Composable
fun HomeSuccessPreview() {
    ShopLiteAppTheme {
        HomeContent(
            uiState = HomeUiState(products = FakeProductSource.products),
            onRetry = {},
            onQueryChange = {},
            onCategorySelected = {},
            onSortSelected = {},
            onProductClick = {})
    }
}