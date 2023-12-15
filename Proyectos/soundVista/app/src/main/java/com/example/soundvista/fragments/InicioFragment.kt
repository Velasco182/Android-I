package com.example.soundvista.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.soundvista.MusicActivity
import com.example.soundvista.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth



class InicioFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)

        val nombreInput = view?.findViewById<TextInputEditText>(R.id.userInput)
        val contrasenaInput= view?.findViewById<TextInputEditText>(R.id.passInput)

        val button = view.findViewById<Button>(R.id.inicioButton)

        button?.setOnClickListener {
            if (nombreInput?.text!!.isNotEmpty() && contrasenaInput?.text!!.isNotEmpty()) {

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        nombreInput.text.toString(),
                        contrasenaInput.text.toString()
                    ).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Obtén el correo electrónico del usuario autenticado exitosamente
                            val user = task.result?.user?.email

                            // Llama a la función InicioFragment con el correo electrónico y ProviderType.BASIC
                            user?.let {
                                InicioFragment(it, ProviderType.BASICO)
                            }
                        } else {
                            ShowAlert()
                        }
                    }
            }
        }

        return view
        // Setup

    }

    private  fun  ShowAlert() {

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog : AlertDialog = builder.create()
        dialog.show()

    }

}