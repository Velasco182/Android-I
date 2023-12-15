package com.example.soundvista.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.fragment.app.Fragment
import com.example.soundvista.ProviderType
import com.example.soundvista.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth



class RegistroFragment : Fragment() {

    val button = view?.findViewById<Button>(R.id.confirmarRegistroButton)

    val nombreInput = view?.findViewById<TextInputEditText>(R.id.nombreInput)
    val apellidoInput = view?.findViewById<TextInputEditText>(R.id.apellidoInput)
    val correoInput= view?.findViewById<TextInputEditText>(R.id.correoInput)
    val contrasenaInput= view?.findViewById<TextInputEditText>(R.id.contraseñaInput)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun InicioFragment(email: String, provider: ProviderType) {
        val homeIntent = Intent(context, InicioFragment::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }


    private  fun  ShowAlert() {

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog : AlertDialog = builder.create()
        dialog.show()

    }



    fun setup() {

        //title ="Autenticacion"


        button?.setOnClickListener {
            if (nombreInput?.text!!.isNotEmpty() && apellidoInput?.text!!.isNotEmpty() && correoInput?.text!!.isNotEmpty()
                && contrasenaInput?.text!!.isNotEmpty()) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    correoInput.text.toString(),
                    contrasenaInput.text.toString()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Obtén el correo electrónico del usuario registrado exitosamente
                        val email = task.result?.user?.email

                        // Llama a la función InicioFragment con el correo electrónico y ProviderType.BASIC
                        email?.let {
                            InicioFragment(it, ProviderType.BASICO)
                        }
                    } else {
                        ShowAlert()
                    }
                }
            }
        }




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registro, container, false)


        val spinner = view?.findViewById<Spinner>(R.id.tipoDocumentoList)

        // Datos de ejemplo para el Spinner
        val items = arrayOf("Cédula de Ciudadanía", "Cédula de Extrajería", "Pasaporte")

        // Crear un adaptador para el Spinner
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)

        // Establecer el diseño del adaptador para el Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Asignar el adaptador al Spinner
        spinner?.adapter = adapter

        // Manejar eventos de selección (igual que en el ejemplo anterior)
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = items[position]
                //Toast.makeText(requireContext(), "Seleccionaste: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Acción a realizar cuando no se selecciona ningún elemento
                val defaultPosition = 0 // o el índice de la opción que deseas seleccionar por defecto
                spinner?.setSelection(defaultPosition)
            }
        }

        ///
        setup()
        return view
        
    }
    
}