package com.aditya.shopliteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aditya.shopliteapp.ui.ShopLiteApp
import com.aditya.shopliteapp.ui.theme.ShopLiteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopLiteAppTheme {
                ShopLiteApp()
            }
        }
    }
}
