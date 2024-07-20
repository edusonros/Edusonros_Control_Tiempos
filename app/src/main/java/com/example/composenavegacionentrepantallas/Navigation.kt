package com.example.composenavegacionentrepantallas

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composenavegacionentrepantallas.screens.*

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("fichador_alot") {
            FichadorALOTScreen(navController)
        }
        composable("qr_trabajo") {
            QRTrabajoScreen(navController)
        }
        composable("qr_preparacion_maquina") {
            QRPreparacionMaquinaScreen(navController)
        }
        composable("qr_auxiliares") {
            QRAuxiliaresScreen(navController)
        }
        composable("qr_medico") {
            QRMedicoScreen(navController)
        }
        composable("configuracion") {
            ConfiguracionScreen(navController)
        }
    }
}
