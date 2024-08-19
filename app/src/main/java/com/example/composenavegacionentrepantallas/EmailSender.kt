package com.example.composenavegacionentrepantallas

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class EmailSender(private val context: Context) {
    private val client = OkHttpClient()

    suspend fun sendEmail(to: String, subject: String, body: String): Boolean {
        val json = JSONObject().apply {
            put("to", to)
            put("subject", subject)
            put("body", body)
        }

        val requestBody = json.toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val request = Request.Builder()
            .url("http://your-server-ip:8080/sendEmail")  // Reemplaza con la URL de tu servidor
            .post(requestBody)
            .build()

        return withContext(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Email sent successfully", Toast.LENGTH_SHORT).show()
                        true
                    } else {
                        Toast.makeText(context, "Failed to send email", Toast.LENGTH_SHORT).show()
                        false
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
                false
            }
        }
    }
}
