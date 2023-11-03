package com.example.pruebaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pruebaapp.data.SharedPreferencesManager
import com.example.pruebaapp.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    lateinit var cadena : String;
    var cadena2 = "";
    val miConst = "Esto es una cadena.";
    private lateinit var sharedPre: SharedPreferencesManager

    ///Binding, extendemos binding con ":" del activity, aparece automáticamente la clase
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ///Binding

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        ///Require context

        setContentView(binding.root)

        Toast.makeText(this, "Hola Mundo", Toast.LENGTH_SHORT).show()

        val valor = sharedPre.getUser()
        //intent.getBooleanExtra() Para pasar booleanos

        val textView = binding.textView.setText(valor)

        Toast.makeText(this, "Bienvenido " + valor, Toast.LENGTH_SHORT).show();

        findViewById<Button>(R.id.button_1).setOnClickListener{

            //val input = findViewById<TextInputEditText>(R.id.textInputEditText).text;

        }
    }




}