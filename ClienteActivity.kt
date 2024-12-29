package com.example.cardapio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.cardapio.databinding.ActivityClienteBinding
import com.google.firebase.auth.FirebaseAuth

class ClienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnDeslogar: Button = findViewById(R.id.btn_deslogar)
        btnDeslogar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val voltarTelaLogin = Intent(this, PrincipalActivity::class.java)
            startActivity(voltarTelaLogin)
            finish()
        }
    }
}