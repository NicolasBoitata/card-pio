package com.example.cardapio

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore

class CadastroActivity : AppCompatActivity() {

    private lateinit var editNome: EditText
    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var btCadastrar: Button
    private val mensagens = arrayOf("preencha todos os campos", "Cadastro Realizado com Sucesso")
    private lateinit var usuarioID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        supportActionBar?.hide()
        iniciarComponentes()

        btCadastrar.setOnClickListener { v ->
            val nome = editNome.text.toString()
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                val snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.WHITE)
                snackbar.setTextColor(Color.BLACK)
                snackbar.show()
            } else {
                cadastrarUsuario(v)
            }
        }
    }

    private fun cadastrarUsuario(view: View) {
        val email = editEmail.text.toString()
        val senha = editSenha.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Handler(Looper.getMainLooper()).postDelayed({
                        IrParaLa()
                    }, 1000)

                    salvarDadosUsuario()
                    val snackbar = Snackbar.make(view, mensagens[1], Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.WHITE)
                    snackbar.setTextColor(Color.BLACK)
                    snackbar.show()
                } else {
                    val erro: String = try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        "Digite uma senha com no mínimo 6 caracteres"
                    } catch (e: FirebaseAuthUserCollisionException) {
                        "Esta conta já foi cadastrada"
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        "E-mail inválido"
                    } catch (e: Exception) {
                        "Erro ao cadastrar usuário"
                    }

                    val snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.WHITE)
                    snackbar.setTextColor(Color.BLACK)
                    snackbar.show()
                }
            }
    }


    private fun IrParaLa() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun salvarDadosUsuario() {
        val nome = editNome.text.toString()
        val db = FirebaseFirestore.getInstance()

        val usuarios = hashMapOf<String, Any>(
            "nome" to nome
        )

        usuarioID = FirebaseAuth.getInstance().currentUser!!.uid

        val documentReference = db.collection("Usuarios").document(usuarioID)
        documentReference.set(usuarios)
            .addOnSuccessListener {
                Log.d("db", "Sucesso ao salvar os dados")
            }
            .addOnFailureListener { e ->
                Log.d("db_erro", "Erro ao salvar os dados: ${e.message}")
            }
    }

    private fun iniciarComponentes() {
        editNome = findViewById(R.id.edit_name)
        editSenha = findViewById(R.id.edit_password)
        editEmail = findViewById(R.id.edit_email)
        btCadastrar = findViewById(R.id.btn_register)
    }
}