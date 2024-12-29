package com.example.cardapio

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var emailFields: EditText
    private lateinit var passwordFields: EditText
    private lateinit var btnLogin: Button
    private val mensagens = arrayOf("Preencha todos os campos", "Login Efetuado com Sucesso")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()  // Esconde a barra de ação
        iniciarComponentes()  // Inicializa os componentes da tela

        // Ao clicar no botão de login
        btnLogin.setOnClickListener { v ->
            val email = emailFields.text.toString()
            val senha = passwordFields.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                val snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.WHITE)
                snackbar.setTextColor(Color.BLACK)
                snackbar.show()
            } else {
                autenticarUsuario(v)  // Chama a função de autenticação
            }
        }
    }

    // Função para classificar o usuário pelo domínio do e-mail
    private fun classifyUserByEmailDomain(email: String) {
        val domain = email.substringAfter("@")

        when (domain) {
            "adm.com" -> {
                // Lógica para Administradores
                Toast.makeText(this, "Bem-vindo, Administração!", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, GestorActivity::class.java)
                startActivity(intent)
                finish()  // Finaliza a atividade de login
            }
            "user.com" -> {
                // Lógica para Usuários (Alunos)
                Toast.makeText(this, "Bem-vindo, Aluno!", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, ClienteActivity::class.java)
                startActivity(intent)
                finish()  // Finaliza a atividade de login
            }
            else -> {
                // Caso o domínio não seja nem "adm.com" nem "user.com"
                Toast.makeText(this, "E-mail inválido. Somente administradores ou usuários são permitidos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    private fun autenticarUsuario(view: View) {
//        val email = emailFields.text.toString()
//        val senha = passwordFields.text.toString()
//
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    // O login foi bem-sucedido, agora verifica a validação no Firestore
//                    val usuarioID = FirebaseAuth.getInstance().currentUser!!.uid
//                    val db = FirebaseFirestore.getInstance()
//
//                    // Consulta o status de validação do usuário
//                    db.collection("Usuarios").document(usuarioID)
//                        .get()
//                        .addOnSuccessListener { document ->
//                            if (document.exists()) {
//                                val validado = document.getBoolean("validado") ?: false
//
//                                if (validado) {
//                                    // Se o usuário estiver validado, permite o login
//                                    val intent = Intent(this, MainActivity::class.java)
//                                    startActivity(intent)
//                                    finish()
//                                } else {
//                                    // Caso não esteja validado, exibe uma mensagem de erro
//                                    val snackbar = Snackbar.make(view, "Seu cadastro ainda está pendente de aprovação.", Snackbar.LENGTH_SHORT)
//                                    snackbar.setBackgroundTint(Color.WHITE)
//                                    snackbar.setTextColor(Color.BLACK)
//                                    snackbar.show()
//                                }
//                            }
//                        }
//                        .addOnFailureListener { exception ->
//                            Log.e("Erro", "Falha ao verificar o status de validação: ${exception.message}")
//                        }
//                } else {
//                    // Em caso de erro de login
//                    val erro: String = try {
//                        throw task.exception!!
//                    } catch (e: Exception) {
//                        "Erro ao autenticar usuário"
//                    }
//
//                    val snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT)
//                    snackbar.setBackgroundTint(Color.WHITE)
//                    snackbar.setTextColor(Color.BLACK)
//                    snackbar.show()
//                }
//            }
//    }




    // Função para autenticar o usuário no Firebase
    private fun autenticarUsuario(view: View) {
        val email = emailFields.text.toString()
        val senha = passwordFields.text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login bem-sucedido, chama a função para classificar o usuário pelo domínio do e-mail
                    // Verifica se o e-mail do usuário foi verificado
                    val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""

//                    if (userEmail != null && userEmail.isEmailVerified) {
//                        // Se o e-mail foi verificado, continua o processo de login

                    Handler().postDelayed({
                        classifyUserByEmailDomain(userEmail)
                    }, 1000) // Aguarda 1 segundo para o redirecionamento

//                        // Se o e-mail não foi verificado, exibe uma mensagem
//                        val snackbar = Snackbar.make(view, "Por favor, verifique seu e-mail antes de continuar", Snackbar.LENGTH_SHORT)
//                        snackbar.setBackgroundTint(Color.WHITE)
//                        snackbar.setTextColor(Color.BLACK)
//                        snackbar.show()
//                    }

                } else {
                    // Caso ocorra um erro na autenticação
                    val erro = try {
                        throw task.exception!!
                    } catch (e: Exception) {
                        "Erro ao logar usuário"
                    }

                    val snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.WHITE)
                    snackbar.setTextColor(Color.BLACK)
                    snackbar.show()
                }
            }
    }

    // Inicializa os componentes da tela
    private fun iniciarComponentes() {
        emailFields = findViewById(R.id.edit_email)
        passwordFields = findViewById(R.id.edit_password)
        btnLogin = findViewById(R.id.btn_logar)
    }

    private fun navegarTelaPrincipal() {
        // Recupera o e-mail do usuário autenticado
        val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""

        // Chama a função de classificação para decidir para qual tela navegar
        classifyUserByEmailDomain(userEmail)
    }

    override fun onStart() {
        super.onStart()

        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null) {
            navegarTelaPrincipal()
        }
    }
//    private fun navegarTelaPrincipal() {
//        val intent = Intent(this, ClienteActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
//    override fun onStart() {
//        super.onStart()
//
//        val usuarioAtual = FirebaseAuth.getInstance().currentUser
//
//        if (usuarioAtual != null) {
//            navegarTelaPrincipal()
//        }
//    }
}
