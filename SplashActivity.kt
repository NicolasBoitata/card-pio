package com.example.cardapio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import java.util.TimerTask

class SplashActivity : AppCompatActivity() {
    private val timer = Timer()
    private lateinit var timerTask: TimerTask

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        timerTask = object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    gotoMainActivity()
                }
            }
        }
        timer.schedule(timerTask, 3000)
    }
    private fun gotoMainActivity() {
        val intent = Intent(applicationContext, PrincipalActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
