package com.example.cardapio.network

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import androidx.appcompat.app.AppCompatActivity
import com.example.cardapio.R

class WebService : AppCompatActivity() {

//    fun getPokemonData(): String? {
//        val url = URL("http://pokeapi.co/api/v2/pokemon/1/")
//
//}
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)
//    val url = URL("http://pokeapi.co/api/v2/pokemon/1/")

//    val webView: WebView = findViewById(R.id.webView)
//    webView.loadUrl("http://192.168.1.x:3000")


//    fun getPokemonData(): String? {
//        val url = URL("http://Localhost:8000")
//        var urlConnection: HttpURLConnection? = null
//        var reader: BufferedReader? = null
//
//        try {
//            urlConnection = url.openConnection() as HttpURLConnection
//            urlConnection.requestMethod = "GET"
//            urlConnection.connect()
//
//            val inputStream = urlConnection.inputStream ?: return null
//
//            reader = BufferedReader(InputStreamReader(inputStream))
//            val buffer = StringBuilder()
//            var line: String?
//            while (reader.readLine().also { line = it } != null) {
//                buffer.append(line + "\n")
//            }
//
//            if (buffer.isEmpty()) {
//                return null
//            }
//
//            return buffer.toString()
//
//        } catch (e: IOException) {
//            Log.e("Erro", "Erro ao obter dados do Pokémon", e)
//            return null
//        } finally {
//            urlConnection?.disconnect()
//            try {
//                reader?.close()
//            } catch (e: IOException) {
//                Log.e("Erro", "Erro fechando o stream", e)
//            }
//        }
//    }
//}
}