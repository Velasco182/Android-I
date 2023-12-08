package com.example.soundvista

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    //private lateinit var sharedPref: SharedPreferencesManager

    private val SPLASH_TIME_OUT: Long = 1500 // 1,5 segundos de demora
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //sharedPref = SharedPreferencesManager(this)

        ///Hacemos la vcalidacion boolean para inicio de sesion
        Handler().postDelayed({
            //if (userLog()){
                // Este código se ejecutará después de SPLASH_TIME_OUT
                val intent = Intent(this, InicioActivity::class.java)
                startActivity(intent)
                finish()
            /*}else{

                val intent1 = Intent(this, LoginActivity::class.java)
                startActivity(intent1)

            }*/

        }, SPLASH_TIME_OUT);
    }

    /*private fun userLog(): Boolean{
        return sharedPref.getBool()
    }*/
}