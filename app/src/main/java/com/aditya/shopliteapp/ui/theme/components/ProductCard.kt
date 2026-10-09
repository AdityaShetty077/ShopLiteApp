package com.aditya.shopliteapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.aditya.shopliteapp.data.fake.FakeProductSource
import com.aditya.shopliteapp.domain.model.Product
import com.aditya.shopliteapp.domain.model.discountedPrice
import com.aditya.shopliteapp.domain.model.toPrice
import com.aditya.shopliteapp.ui.theme.ShopLiteAppTheme

@Composable
fun ProductCard(product: Product, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {                                   // ①
        Column {                                                  // ②
            AsyncImage(                                           // ③
                model = product.thumbnail,
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            )
            Column(modifier = Modifier.padding(8.dp)) {           // ④
                Text(                                             // ⑤
                    text = product.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(                                              // ⑥
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = product.discountedPrice().toPrice(),
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "★ ${product.rating}")
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 180)                    // ⑦
@Composable
fun ProductCardPreview() {
    ShopLiteAppTheme {
        ProductCard(product = FakeProductSource.products[2])
    }
}