package com.example.composenavegacionentrepantallas.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composenavegacionentrepantallas.R
import com.journeyapps.barcodescanner.ScanOptions
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

private val Gray = Color(0xFFE0E0E0)

@Composable
fun QRPreparacionMaquinaScreen(navController: NavHostController) {
    var operario by remember { mutableIntStateOf(0) }
    var pedido by remember { mutableIntStateOf(0) }
    var operacion by remember { mutableIntStateOf(0) }
    var datosProceso by remember { mutableStateOf<String?>(null) }
    var isEmailReady by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val scanLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data = result.data
        if (result.resultCode == android.app.Activity.RESULT_OK && data != null) {
            val contents = data.getStringExtra("SCAN_RESULT")
            if (contents != null && contents.length == 3) {
                operario = contents.toIntOrNull() ?: 0
                Toast.makeText(context, "Operario: $operario", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Código QR del operario incorrecto", Toast.LENGTH_SHORT).show()
            }
        }
    }

    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data = result.data
        if (result.resultCode == android.app.Activity.RESULT_OK && data != null) {
            val contents = data.getStringExtra("SCAN_RESULT")
            if (contents != null && contents.length == 9) {
                datosProceso = contents
                Toast.makeText(context, "Proceso: $datosProceso", Toast.LENGTH_SHORT).show()
                if (datosProceso != null && datosProceso!!.length >= 9) {
                    pedido = datosProceso!!.substring(0, 5).toIntOrNull() ?: 0
                    operacion = datosProceso!!.substring(5, 9).toIntOrNull() ?: 0
                    isEmailReady = true  // Activamos el botón "Confirmar Envío" cuando los datos del proceso estén completos
                }
            } else {
                Toast.makeText(context, "Código QR del proceso incorrecto", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Gray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_alot_metal),
                    contentDescription = "Logo",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "PREPARACIÓN\nMAQUINAS",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                        maxLines = 2
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    TextField(
                        value = if (pedido == 0) "" else pedido.toString(),
                        onValueChange = { input -> pedido = input.toIntOrNull() ?: 0 },
                        label = { Text("Introducir Nº de ORDEN:") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    )
                }
                item { Spacer(modifier = Modifier.height(40.dp)) }
                item {
                    Button(
                        onClick = {
                            val options = ScanOptions()
                            options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                            options.setPrompt("Preparar Maquina THOR")
                            options.setCameraId(0)
                            scanLauncher.launch(Intent(context, com.journeyapps.barcodescanner.CaptureActivity::class.java))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text(text = "Preparar Maquina THOR: INICIO")
                    }
                }
                item { Spacer(modifier = Modifier.height(20.dp)) }
                item {
                    Button(
                        onClick = {
                            val options = ScanOptions()
                            options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                            options.setPrompt("Preparar Maquina THOR")
                            options.setCameraId(0)
                            scanLauncher.launch(Intent(context, com.journeyapps.barcodescanner.CaptureActivity::class.java))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text(text = "Preparar Maquina THOR: FINAL")
                    }
                }
                item { Spacer(modifier = Modifier.height(50.dp)) }
                item {
                    Button(
                        onClick = {
                            val options = ScanOptions()
                            options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                            options.setPrompt("Preparar Maquina LASER")
                            options.setCameraId(0)
                            scanLauncher.launch(Intent(context, com.journeyapps.barcodescanner.CaptureActivity::class.java))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text(text = "Preparar Maquina LASER: INICIO")
                    }
                }
                item { Spacer(modifier = Modifier.height(20.dp)) }
                item {
                    Button(
                        onClick = {
                            val options = ScanOptions()
                            options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                            options.setPrompt("Preparar Maquina LASER")
                            options.setCameraId(0)
                            scanLauncher.launch(Intent(context, com.journeyapps.barcodescanner.CaptureActivity::class.java))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text(text = "Preparar Maquina LASER: FINAL")
                    }
                }
                item { Spacer(modifier = Modifier.height(50.dp)) }
                item {
                    Button(
                        onClick = {
                            val options = ScanOptions()
                            options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                            options.setPrompt("Preparar Maquina MECANIZADO")
                            options.setCameraId(0)
                            scanLauncher.launch(Intent(context, com.journeyapps.barcodescanner.CaptureActivity::class.java))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text(text = "Preparar Maquina MECANIZADO: INICIO")
                    }
                }
                item { Spacer(modifier = Modifier.height(20.dp)) }
                item {
                    Button(
                        onClick = {
                            val options = ScanOptions()
                            options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                            options.setPrompt("Preparar Maquina MECANIZADO")
                            options.setCameraId(0)
                            scanLauncher.launch(Intent(context, com.journeyapps.barcodescanner.CaptureActivity::class.java))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text(text = "Preparar Maquina MECANIZADO: FINAL")
                    }
                }
                item { Spacer(modifier = Modifier.height(50.dp)) }
                item {
                    Button(
                        onClick = {
                            val options = ScanOptions()
                            options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                            options.setPrompt("Preparar Maquina TALADRO")
                            options.setCameraId(0)
                            scanLauncher.launch(Intent(context, com.journeyapps.barcodescanner.CaptureActivity::class.java))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text(text = "Preparar Maquina TALADRO: INICIO")
                    }
                }
                item { Spacer(modifier = Modifier.height(20.dp)) }
                item {
                    Button(
                        onClick = {
                            val options = ScanOptions()
                            options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                            options.setPrompt("Preparar Maquina TALADRO")
                            options.setCameraId(0)
                            scanLauncher.launch(Intent(context, com.journeyapps.barcodescanner.CaptureActivity::class.java))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text(text="Preparar Maquina TALADRO: FINAL")
                    }
            }}
        }
    }
}

fun sendEmailPreparacion(context: Context, operario: Int, pedido: Int, operacion: Int) {
    val fechaActual = LocalDate.now()
    val horaActual = LocalTime.now()

    val formatterFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss")
    val fechaFormateada = fechaActual.format(formatterFecha)
    val horaFormateada = horaActual.format(formatterHora)

    val datosCorreo = "$operario;$pedido;$operacion;$fechaFormateada;$horaFormateada"

    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf("tecnica@alotmetal.com"))
        putExtra(Intent.EXTRA_SUBJECT, datosCorreo)
        putExtra(Intent.EXTRA_TEXT, datosCorreo)
    }

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "No hay aplicaciones de correo disponibles", Toast.LENGTH_SHORT).show()
    }
}

@Preview(showBackground = true)
@Composable
fun QRPreparacionMaquinaScreenPreview() {
    QRPreparacionMaquinaScreen(rememberNavController())
}
