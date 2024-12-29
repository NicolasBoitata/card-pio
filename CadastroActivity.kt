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



//    private fun cadastrarUsuario(view: View) {
//        val email = editEmail.text.toString()
//        val senha = editSenha.text.toString()
//
//        // Cria o usuário normalmente no Firebase
//        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    // O cadastro foi realizado com sucesso
//                    val usuario = FirebaseAuth.getInstance().currentUser
//
//                    // Salva o usuário no Firestore com um campo 'validado' inicial como false
//                    salvarDadosUsuario()
//
//                    // Envia uma notificação para o administrador para validar o novo cadastro
//                    enviarNotificacaoAdministrador(email)
//
//                    // Mensagem de sucesso para o usuário
//                    val snackbar = Snackbar.make(view, "Cadastro realizado! Aguardando validação.", Snackbar.LENGTH_SHORT)
//                    snackbar.setBackgroundTint(Color.WHITE)
//                    snackbar.setTextColor(Color.BLACK)
//                    snackbar.show()
//
//                    // Redireciona o usuário para a tela de login
//                    Handler(Looper.getMainLooper()).postDelayed({
//                        IrParaLa()
//                    }, 1500)
//
//                } else {
//                    // Em caso de erro no cadastro, trata as exceções
//                    val erro: String = try {
//                        throw task.exception!!
//                    } catch (e: FirebaseAuthWeakPasswordException) {
//                        "Digite uma senha com no mínimo 6 caracteres"
//                    } catch (e: FirebaseAuthUserCollisionException) {
//                        "Esta conta já foi cadastrada"
//                    } catch (e: FirebaseAuthInvalidCredentialsException) {
//                        "E-mail inválido"
//                    } catch (e: Exception) {
//                        "Erro ao cadastrar usuário"
//                    }
//
//                    val snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT)
//                    snackbar.setBackgroundTint(Color.WHITE)
//                    snackbar.setTextColor(Color.BLACK)
//                    snackbar.show()
//                }
//            }
//    }
//
//    private fun salvarDadosUsuario() {
//        val nome = editNome.text.toString()
//        val db = FirebaseFirestore.getInstance()
//
//        // Cria o mapa de dados do usuário para salvar no Firestore
//        val usuarios = hashMapOf<String, Any>(
//            "nome" to nome,
//            "validado" to false  // Campo 'validado' como 'false' inicialmente
//        )
//
//        // Pega o ID do usuário criado
//        usuarioID = FirebaseAuth.getInstance().currentUser!!.uid
//
//        // Salva no Firestore
//        val documentReference = db.collection("Usuarios").document(usuarioID)
//        documentReference.set(usuarios)
//            .addOnSuccessListener {
//                Log.d("db", "Sucesso ao salvar os dados")
//            }
//            .addOnFailureListener { e ->
//                Log.d("db_erro", "Erro ao salvar os dados: ${e.message}")
//            }
//    }
//
//    private fun enviarNotificacaoAdministrador(email: String) {
//        // Aqui você pode criar uma lógica para enviar um e-mail ou alguma notificação para o administrador
//        // Este é apenas um exemplo de como poderia ser feito:
//
//        val administradorEmail = "nicolasgomescamelo@gmail.com"
//        val assunto = "Novo cadastro pendente de validação"
//        val corpo = "O novo usuário com o e-mail $email se cadastrou no sistema. Por favor, valide o cadastro."
//
//        // Aqui, você pode usar um serviço de e-mail (como SMTP, Firebase Functions ou um serviço externo) para enviar a notificação.
//        // **Essa parte do envio de e-mail precisa ser implementada de forma segura, com um servidor ou função externa**.
//
//        Log.d("Notificacao", "Administrador será notificado sobre o cadastro de: $email")
//    }





//    private fun cadastrarUsuario(view: View) {
//        val email = editEmail.text.toString()
//        val senha = editSenha.text.toString()
//
//        // Cadastra o usuário normalmente
//        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    // Verifica se o e-mail é permitido (por exemplo, nicolasgomescamelo@gmail.com)
//                    if (validarEmail(email)) {
//                        // Envia o e-mail de verificação, caso o e-mail seja válido
//                        val usuario = FirebaseAuth.getInstance().currentUser
//                        usuario?.sendEmailVerification()?.addOnCompleteListener { emailTask ->
//                            if (emailTask.isSuccessful) {
//                                Handler(Looper.getMainLooper()).postDelayed({
//                                    IrParaLa()  // Redireciona para a tela de login
//                                }, 1500)
//
//                                salvarDadosUsuario()  // Salva os dados no Firestore
//                                val snackbar = Snackbar.make(view, mensagens[1], Snackbar.LENGTH_SHORT)
//                                snackbar.setBackgroundTint(Color.WHITE)
//                                snackbar.setTextColor(Color.BLACK)
//                                snackbar.show()
//                            } else {
//                                val erro = "Erro ao enviar e-mail de verificação"
//                                val snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT)
//                                snackbar.setBackgroundTint(Color.WHITE)
//                                snackbar.setTextColor(Color.BLACK)
//                                snackbar.show()
//                            }
//                        }
//                    } else {
//                        // Se o e-mail não for permitido, exibe uma mensagem de erro
//                        FirebaseAuth.getInstance().currentUser?.delete()  // Apaga o usuário recém-criado
//                        val erro = "Este e-mail não é permitido para cadastro"
//                        val snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT)
//                        snackbar.setBackgroundTint(Color.WHITE)
//                        snackbar.setTextColor(Color.BLACK)
//                        snackbar.show()
//                    }
//                } else {
//                    // Erros do Firebase
//                    val erro: String = try {
//                        throw task.exception!!
//                    } catch (e: FirebaseAuthWeakPasswordException) {
//                        "Digite uma senha com no mínimo 6 caracteres"
//                    } catch (e: FirebaseAuthUserCollisionException) {
//                        "Esta conta já foi cadastrada"
//                    } catch (e: FirebaseAuthInvalidCredentialsException) {
//                        "E-mail inválido"
//                    } catch (e: Exception) {
//                        "Erro ao cadastrar usuário"
//                    }
//
//                    val snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT)
//                    snackbar.setBackgroundTint(Color.WHITE)
//                    snackbar.setTextColor(Color.BLACK)
//                    snackbar.show()
//                }
//            }
//    }
//    private fun validarEmail(email: String): Boolean {
//        // Define os e-mails permitidos (aqui o admin pode incluir mais e-mails, por exemplo)
//        val emailsPermitidos = listOf("nicolasgomescamelo@gmail.com", "outroemailpermitido@example.com")
//
//        return emailsPermitidos.contains(email)
//    }



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