package com.example.composenavegacionentrepantallas.screens

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composenavegacionentrepantallas.R
import com.google.android.gms.location.LocationServices
import com.journeyapps.barcodescanner.ScanOptions
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import com.example.composenavegacionentrepantallas.data.Coordinates
import com.example.composenavegacionentrepantallas.data.CoordenadasFichadorALOT

private val Gray = Color(0xFFE0E0E0)


@Composable
fun FichadorALOTScreen(navController: NavHostController) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val REQUEST_LOCATION_PERMISSION = 1

    var operario by remember { mutableStateOf("") }
    var entradaHora by remember { mutableStateOf("") }
    var salidaHora by remember { mutableStateOf("") }
    var coordenadas by remember { mutableStateOf(CoordenadasFichadorALOT.coordenadas.toList()) }
    //var selectedCoordinates by remember { mutableStateOf<Coordinates?>(null) }
    //Coordenada de ALOT:
    //var coordenadas by remember { mutableStateOf(Pair(41.624163674595984, -0.7332491321666281)) }
    //Coordenadas de Lorente:
    //var coordenadas by remember { mutableStateOf(Pair(41.646431984478454, -0.8952907790250264)) }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }
    var currentButton by remember { mutableStateOf("") }


    fun checkLocationPermission(): Boolean {
        val permission = ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
        return permission == PackageManager.PERMISSION_GRANTED
    }

    fun requestLocationPermission(activity: Activity) {
        if (!checkLocationPermission()) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    // Verificar y solicitar permisos
    LaunchedEffect(Unit) {
        requestLocationPermission(context as Activity)
    }

    fun getLastKnownLocation(onLocationReceived: (Location) -> Unit) {
        if (checkLocationPermission()) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    Toast.makeText(context, "Ubicación obtenida: ${location.latitude}, ${location.longitude}", Toast.LENGTH_SHORT).show()
                    onLocationReceived(location)
                } else {
                    Toast.makeText(context, "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(context, "Error al obtener la ubicación: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Permiso de ubicación no concedido", Toast.LENGTH_SHORT).show()
        }
    }

    val scanLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val intent = result.data
        val contents = intent?.getStringExtra("SCAN_RESULT")
        if (contents != null) {
            operario = contents
            Toast.makeText(context, "Operario $operario escaneado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Escaneo fallido", Toast.LENGTH_SHORT).show()
        }
    }

    fun scanQR() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Escanear QR del Operario")
        options.setCameraId(0)
        val intent = options.createScanIntent(context)
        scanLauncher.launch(intent)
    }

    fun isWithinRange(targetLat: Double, targetLon: Double, currentLat: Double, currentLon: Double): Boolean {
        val results = FloatArray(1)
        Location.distanceBetween(targetLat, targetLon, currentLat, currentLon, results)
        return results[0] < 1000 // 1 km
    }

    fun handleFichaje(action: String) {
        getLastKnownLocation { location ->
            // Obtener la primera coordenada de la lista para verificar
            val selectedCoordinate = CoordenadasFichadorALOT.coordenadas.firstOrNull()

            if (selectedCoordinate != null) {
                val isWithinRange = isWithinRange(
                    targetLat = selectedCoordinate.latitud,
                    targetLon = selectedCoordinate.longitud,
                    currentLat = location.latitude,
                    currentLon = location.longitude
                )

                if (isWithinRange) {
                    val currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                    if (action == "entrada") {
                        entradaHora = currentTime
                        dialogMessage = "¿Quiere confirmar el fichaje de entrada?"
                    } else if (action == "salida") {
                        salidaHora = currentTime
                        dialogMessage = "¿Quiere confirmar el fichaje de salida?"
                    }
                    currentButton = action
                    showDialog = true
                } else {
                    Toast.makeText(context, "Fuera del rango permitido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "No hay coordenadas seleccionadas", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun sendEmail(subject: String, body: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("tecnica@alotmetal.com", "edusonros@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }
        context.startActivity(intent)
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Confirmar Fichaje") },
            text = { Text(text = dialogMessage) },
            confirmButton = {
                Button(onClick = {
                    val currentDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
                    val subject = "${currentButton.capitalize()} $operario"
                    val body = "Fecha: $currentDate\nHora: ${if (currentButton == "entrada") entradaHora else salidaHora}\nOperario: $operario"
                    sendEmail(subject, body)
                    showDialog = false
                }) {
                    Text("Sí")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("No")
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_alot_metal),
                    contentDescription = "Logo",
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Box(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "FICHADOR ALOT",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 1
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { handleFichaje("entrada") },
                    enabled = operario.isNotEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(text = "Fichar ENTRADA")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Hora de entrada: $entradaHora",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { scanQR() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(text = "Escanear QR del Operario")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { handleFichaje("salida") },
                    enabled = operario.isNotEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(text = "Fichar SALIDA")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Hora de salida: $salidaHora",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FichadorALOTScreenPreview() {
    FichadorALOTScreen(rememberNavController())
}
