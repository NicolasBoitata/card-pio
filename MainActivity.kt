package com.example.cardapio

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Executa a requisição assíncrona
//        MyHttpRequestTask().execute("http://192.168.1.x:3000/api") // Para dispositivos físicos
//         MyHttpRequestTask().execute("http://10.0.2.2:3000/api") // Para emuladores
    }

    // AsyncTask para realizar a requisição HTTP
//    private class MyHttpRequestTask : AsyncTask<String, Void, String>() {
//
//        override fun doInBackground(vararg params: String?): String? {
//            val urlString = params[0]
//            val url = URL(urlString)
//            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
//
//            try {
//                urlConnection.requestMethod = "GET"
//                urlConnection.connect()
//
//                // Ler a resposta
//                val inputStream = urlConnection.inputStream.bufferedReader().use { it.readText() }
//                return inputStream
//            } finally {
//                urlConnection.disconnect()
//            }
//        }
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            // Aqui você pode atualizar a UI com o resultado da requisição
//        }
//    }
}