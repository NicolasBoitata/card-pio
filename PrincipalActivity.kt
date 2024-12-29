package com.example.cardapio

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PrincipalActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val btnEntrando: Button = findViewById(R.id.btn_entrando)
        val btnCadastrando: Button = findViewById(R.id.btn_cadastrando)

        btnCadastrando.setOnClickListener {
            val navegarLogin = Intent(this, CadastroActivity::class.java)
            startActivity(navegarLogin)
        }
        btnEntrando.setOnClickListener {
            val navegarCadastro = Intent(this, LoginActivity::class.java)
            startActivity(navegarCadastro)
        }
    }
}