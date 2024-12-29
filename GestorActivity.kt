package com.example.cardapio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.cardapio.databinding.ActivityGestorBinding
import com.google.firebase.auth.FirebaseAuth

class GestorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGestorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGestorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnDeslogar: Button = findViewById(R.id.btn_deslogar)
        btnDeslogar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()  // Faz o logout do administrador
            val voltarTelaLogin = Intent(this, PrincipalActivity::class.java)  // Redireciona para a tela de login
            startActivity(voltarTelaLogin)
            finish()  // Finaliza a atividade atual
        }
    }
}