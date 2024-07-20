package com.example.composenavegacionentrepantallas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.composenavegacionentrepantallas.ui.theme.ComposeNavegacionEntrePantallasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavegacionEntrePantallasTheme {
                val navController = rememberNavController()
                NavigationGraph(navController)
            }
        }
    }
}
