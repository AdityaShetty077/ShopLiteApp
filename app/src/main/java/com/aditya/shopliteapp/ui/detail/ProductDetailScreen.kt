package com.aditya.shopliteapp.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.aditya.shopliteapp.data.fake.FakeProductSource
import com.aditya.shopliteapp.domain.model.Product
import com.aditya.shopliteapp.domain.model.brandOrDefault
import com.aditya.shopliteapp.domain.model.discountedPrice
import com.aditya.shopliteapp.domain.model.toPrice
import com.aditya.shopliteapp.ui.theme.ShopLiteAppTheme
import kotlin.math.roundToInt

@Composable
fun ProductDetailScreen(
    onBack: () -> Unit,
    onAddedToCart: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel()      // Android creates it (with the route's SavedStateHandle)
) {
    // watch the ViewModel's state; redraws when it changes
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProductDetailContent(
        uiState = uiState,
        onBack = onBack,                          // just pass it through to the NavHost
        onRetry = { viewModel.loadProduct() },    // Retry → reload
        onAddToCart = {viewModel.addToCart()
                      onAddedToCart()},
        modifier = modifier
    )
}

@Composable
fun ProductDetailContent(
    uiState: DetailUiState,
    onBack: () -> Unit,
    onRetry: () -> Unit,
    onAddToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        // 1. loading → spinner in the middle
        uiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        // 2. error → message + Retry + a way back
        uiState.errorMessage != null -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = uiState.errorMessage)
                Button(onClick = onRetry) { Text("Retry") }
                TextButton(onClick = onBack) { Text("← Back") }
            }
        }

        // 3. loaded → the real page
        uiState.product != null -> {
            ProductDetails(
                product = uiState.product,
                onBack = onBack,
                onAddToCart = onAddToCart,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ProductDetails(
    product: Product,
    onBack: () -> Unit,
    onAddToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())     // lets the page scroll
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)  // 8dp gap between every child
    ) {
        // 1. back
        TextButton(onClick = onBack) { Text("← Back") }

        // 2. image
        AsyncImage(
            model = product.thumbnail,
            contentDescription = product.title,
            contentScale = ContentScale.Fit,            // whole image visible, no cropping
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        )

        // 3. title + brand
        Text(text = product.title, style = MaterialTheme.typography.headlineSmall)
        Text(text = product.brandOrDefault(), color = MaterialTheme.colorScheme.onSurfaceVariant)

        // 4. prices: discounted · original (crossed out) · discount %
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = product.discountedPrice().toPrice(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = product.price.toPrice(),
                textDecoration = TextDecoration.LineThrough,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "-${product.discountPercentage.roundToInt()}%",
                color = MaterialTheme.colorScheme.error
            )
        }

        // 5. rating + stock
        Text(text = "★ ${product.rating} · ${product.stock} in stock")

        // 6. description
        Text(text = product.description, style = MaterialTheme.typography.bodyLarge)

        // 7. add to cart (Day 13)
        Button(
            onClick = onAddToCart,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add to cart")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailsPreview() {
    ShopLiteAppTheme {
        ProductDetails(
            product = FakeProductSource.products[0],
            onBack = {},
            onAddToCart = {})
    }
}

@Preview(name = "Detail loading", showBackground = true)
@Composable
fun DetailLoadingPreview() {
    ShopLiteAppTheme {
        ProductDetailContent(
            uiState = DetailUiState(isLoading = true),
            onBack = {},
            onRetry = {},
            onAddToCart = {})
    }
}

@Preview(name = "Detail error", showBackground = true)
@Composable
fun DetailErrorPreview() {
    ShopLiteAppTheme {
        ProductDetailContent(
            uiState = DetailUiState(errorMessage = "404 : Server Error"),
            onBack = {},
            onRetry = {},
            onAddToCart = {})
    }
}