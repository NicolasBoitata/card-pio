package com.example.cardapio.network

import android.os.AsyncTask
import java.net.HttpURLConnection
import java.net.URL
import android.telecom.Call
import android.widget.EditText
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URLConnection

//import io.ktor.application.*
//import io.ktor.http.*
//import io.ktor.request.*
//import io.ktor.response.*
//import io.ktor.routing.*
//import io.ktor.server.engine.embeddedServer
//import io.ktor.server.netty.Netty
//import io.ktor.features.ContentNegotiation

//import io.ktor.application.*
//import io.ktor.features.ContentNegotiation
//import io.ktor.http.ContentType
//import io.ktor.request.receive
//import io.ktor.response.respond
//import io.ktor.response.respondText
//import io.ktor.routing.get importio.ktor.routing.post
//import io.ktor.routing.routing
//import io.ktor.serialization.gson
//import io.ktor.server.engine.embeddedServer
//import io.ktor.server.netty.Netty

//fun main() {
//    embeddedServer(Netty, port = 8080) {
//        install(ContentNegotiation) {
//            gson {
//                // Configuração do Gson
//            }
//        }
//        routing {
//            get("/") {
//                call.respondText("Bem-vindo ao servidor Ktor no localhost!", ContentType.Text.Plain)
//            }
//            post("/data") {
//                val data = call.receive<Map<String, Any>>()
//                call.respond(mapOf("message" to "Dados recebidos", "data" to data))
//            }
//        }
//    }.start(wait = true)
//}



//const val URL = "http://Localhost:8000/"
//const val URL = "http://192.168.1.2:5000"
//val url = URL("http://127.0.0.1:8000")// IP especial para acessar o localhost da máquina
//URL("http://pokeapi.co/api/v2/pokemon/1/")

//class ApiMensagem : AsyncTask<Void, Int, String>() {
//
//    @Override
//    override fun doInBackground (vararg params: Void?): String { /*facaEmSegundoPlano*/
//        // Esta etapa é usada para executar a tarefa em background ou fazer a chamada           para o webservice
////        return TODO("Provide the return value")
////        return "Result"
//        return try {
//            URL("http://192.168.56.1.x:8000").openConnection().use { urlConnection ->
//                (urlConnection as HttpURLConnection).apply {
//                    requestMethod = "GET"
//                    connect()
//                }
//                urlConnection.inputStream.use { inputStream ->
//                    BufferedReader(InputStreamReader(inputStream)).use { reader ->
//                        val buffer = StringBuilder()
//                        var line: String?
//                        while (reader.readLine().also { line = it } != null) {
//                            buffer.append(line).append("\n")
//                        }
//                        buffer.toString()
//                    }
//                }
//            }
//        } catch (e: Exception){
//            e.printStackTrace()
//            null
//        }.toString()
//    }
//
//    @Override
//    override fun onPreExecute () {/*emPreExecutar*/
//        // Este passo é usado para configurar a tarefa, por exemplo, mostrando uma barra de progresso na interface do usuário.
//    }
//
//    @Override
//    override fun onProgressUpdate (vararg values: Int?) {/*emAtualizacaoDeProgresso*/
//        // Este método é usado para exibir qualquer forma de progresso na interface do usuário, enquanto a tarefa ainda está em execução.
//    }
//
//    @Override
//    override fun onPostExecute (result: String?) {/*emPosExecucao*/
//        // O resultado da execução em background é passado para este passo como um parâmetro.
//    }


//    override fun doInBackground(vararg params: Void?): String {
//        val url = URL("http://127.0.0.1:8000") // IP do seu computador e a porta
//        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
//        try {
//            val inputStream = urlConnection.inputStream
//            return inputStream.bufferedReader().use { it.readText() }
//        } finally {
//            urlConnection.disconnect()
//        }
//    }
//
//    override fun onPostExecute(result: String?) {
//        super.onPostExecute(result)
//        // Aqui você pode atualizar a UI com a resposta
//    }

//private fun URLConnection.use(block: Function1<*, *>){}
//}



//interface ApiMensagem {
//
//    @GET("lu")
//    fun listarDados(@Query("q") q: String, @Query("name") name : String) : Call<List<Dados>>
//
//    @POST("dados")
//    fun inserirDado(@Body userData: DadosPost) : Call<DadosPost>
//}
//
//object Servicos {
//
//        val instancia : ApiMensagem
//
//        init {
//            val retrofit = Retrofit.Builder()
//                .baseUrl(URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//            instancia = retrofit.create(ApiMensagem::class.java)
//        }
//
//}