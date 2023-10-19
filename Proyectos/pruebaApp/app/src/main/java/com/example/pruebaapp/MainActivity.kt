package com.example.pruebaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    lateinit var cadena : String;
    var cadena2 = "";
    val miConst = "Esto es una cadena.";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_1).setOnClickListener{

            //Toast.makeText(this, "Hola Mundo", Toast.LENGTH_SHORT).show();

            var input = findViewById<TextInputEditText>(R.id.textInputEditText).text;
            findViewById<TextView>(R.id.textView).setText(input);


        }
    }


}