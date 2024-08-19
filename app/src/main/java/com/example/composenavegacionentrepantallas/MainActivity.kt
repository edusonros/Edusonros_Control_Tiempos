package com.example.composenavegacionentrepantallas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.composenavegacionentrepantallas.ui.theme.ComposeNavegacionEntrePantallasTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var emailSender: EmailSender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emailSender = EmailSender(this)

        setContent {
            ComposeNavegacionEntrePantallasTheme {
                val navController = rememberNavController()
                NavigationGraph(navController = navController)
            }
        }

        // Example call to send email
        CoroutineScope(Dispatchers.Main).launch {
            emailSender.sendEmail(
                "recipient@example.com",
                "Test Email",
                "This is a test email from our Kotlin server."
            )
        }
    }
}
