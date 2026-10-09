package com.aditya.shopliteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aditya.shopliteapp.data.fake.FakeProductSource
import com.aditya.shopliteapp.ui.components.ProductCard
import com.aditya.shopliteapp.ui.theme.ShopLiteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopLiteAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProductCard(
                        product = FakeProductSource.products[0],
                        modifier = Modifier
                            .padding(innerPadding)
                            .width(180.dp)
                    )
                }
            }
        }
    }
}