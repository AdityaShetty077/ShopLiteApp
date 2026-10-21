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
import com.aditya.shopliteapp.domain.model.Product
import com.aditya.shopliteapp.domain.model.brandOrDefault
import com.aditya.shopliteapp.domain.model.discountedPrice
import com.aditya.shopliteapp.domain.model.toPrice
import kotlin.math.roundToInt

@Composable
fun ProductDetailScreen(
    onBack: () -> Unit,
    onAddedToCart: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProductDetailContent(
        uiState = uiState,
        onBack = onBack,
        onRetry = { viewModel.loadProduct() },
        onAddToCart = {
            viewModel.addToCart()
            onAddedToCart()
        },
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
        uiState.isLoading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
                Button(onClick = onRetry) { Text("Retry") }
                TextButton(onClick = onBack) { Text("← Back") }
            }
        }
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
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextButton(onClick = onBack) { Text("← Back") }

        AsyncImage(
            model = product.thumbnail,
            contentDescription = product.title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        )

        Text(text = product.title, style = MaterialTheme.typography.headlineSmall)
        Text(text = product.brandOrDefault(), color = MaterialTheme.colorScheme.onSurfaceVariant)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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

        Text(text = "★ ${product.rating} · ${product.stock} in stock")

        Text(text = product.description, style = MaterialTheme.typography.bodyLarge)

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
fun ProductDetailContentPreview() {
    val product = Product(
        id = 1,
        title = "iPhone 16 Pro",
        description = "Premium smartphone with an advanced camera system and powerful performance.",
        category = "smartphones",
        price = 1199.0,
        discountPercentage = 10.0,
        rating = 4.8,
        stock = 25,
        brand = "Apple",
        thumbnail = "",
        images = emptyList()
    )

    MaterialTheme {
        ProductDetails(
            product = product,
            onBack = {},
            onAddToCart = {}
        )
    }
}