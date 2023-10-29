package com.example.pruebaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 2000 // 2 segundos de demora
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({

            // Este código se ejecutará después de SPLASH_TIME_OUT
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }, SPLASH_TIME_OUT);
    }
}