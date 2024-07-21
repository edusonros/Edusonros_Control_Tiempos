package com.example.composenavegacionentrepantallas.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.*
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import android.util.Log

class EmailWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.d("EmailWorker", "Worker started")

        val operario = inputData.getInt("operario", 0)
        val pedido = inputData.getInt("pedido", 0)
        val operacion = inputData.getInt("operacion", 0)
        val fechaFormateada = inputData.getString("fechaFormateada") ?: ""
        val horaFormateada = inputData.getString("horaFormateada") ?: ""

        Log.d("EmailWorker", "Received data - Operario: $operario, Pedido: $pedido, Operacion: $operacion, Fecha: $fechaFormateada, Hora: $horaFormateada")

        return try {
            sendEmail(operario, pedido, operacion, fechaFormateada, horaFormateada)
            Log.d("EmailWorker", "Email sent successfully")
            Result.success()
        } catch (e: Exception) {
            Log.e("EmailWorker", "Failed to send email", e)
            Result.failure()
        }
    }

    private fun sendEmail(operario: Int, pedido: Int, operacion: Int, fechaFormateada: String, horaFormateada: String) {
        val props = Properties().apply {
            put("mail.smtp.host", "smtp.gmail.com")
            put("mail.smtp.port", "587")
            put("mail.smtp.auth", "true")
            put("mail.smtp.starttls.enable", "true")
        }

        val session = Session.getInstance(props, object : javax.mail.Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication("edusonros@gmail.com",
                    "Dmpsoncar69")
            }
        })

        val message = MimeMessage(session).apply {
            setFrom(InternetAddress("edusonros@gmail.com"))
            setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse("tecnica@alotmetal.com")
            )
            subject = "$operario;$pedido;$operacion;$fechaFormateada;$horaFormateada"
            setText("Operario: $operario\nPedido: $pedido\nOperación: $operacion")
        }

        Log.d("EmailWorker", "Sending email...")
        Transport.send(message)
    }
}
