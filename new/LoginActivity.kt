package com.example.cardapio

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

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

    // Função para autenticar o usuário no Firebase
    private fun autenticarUsuario(view: View) {
        val email = emailFields.text.toString()
        val senha = passwordFields.text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""

                    Handler().postDelayed({
                        classifyUserByEmailDomain(userEmail)
                    }, 1000)
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
}
