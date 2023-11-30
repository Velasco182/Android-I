package com.example.componentsapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.componentsapp.fragments.JuanFragment
import com.example.componentsapp.fragments.CarlosFragment
import com.example.componentsapp.fragments.ChikenFragment
import com.example.componentsapp.fragments.DanielFragment
import com.example.componentsapp.fragments.DiegoFragment
import com.example.componentsapp.fragments.LuisFragment
import com.example.componentsapp.fragments.ManuelFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val arrayAdapter : ArrayAdapter<*>
        val personas = mutableListOf("Películas", "Series", "Documentales", "Futbol en vivo", "Luis", "Manuel", "Chicken Little")
        val lvDatos = findViewById<ListView>(R.id.lvDatos)

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, personas)
        lvDatos.adapter = arrayAdapter

        replaceFragment(JuanFragment(), "JuanFragment")

        val spinner = findViewById<Spinner>(R.id.spinnerSeries)

        // Obtener el array desde resources
        val opcion = resources.getStringArray(R.array.genero)

        // Configurar el adapter para el Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opcion)

        // Configurar el estilo del dropdown (opcional)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Configurar el adapter en el Spinner
        spinner.adapter = adapter

        spinner.visibility = View.INVISIBLE

        // Manejar eventos de selección (igual que en el ejemplo anterior)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = opcion[position]
                Toast.makeText(this@MainActivity, "Seleccionaste: $selectedItem", Toast.LENGTH_SHORT).show()

                val carlosFragment = supportFragmentManager.findFragmentByTag("CarlosFragment") as? CarlosFragment
                //val opcionSeleccionada = opcion[position]
                carlosFragment?.cargarContenidoSegunOpcion(selectedItem)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Acción a realizar cuando no se selecciona ningún elemento
            }
        }

        /*spinner.setOnItemClickListener(){ parent, view, position, id ->

                val carlosFragment = supportFragmentManager.findFragmentByTag("CarlosFragment") as? CarlosFragment
                val opcionSeleccionada = parent.getItemAtPosition(position).toString()
                carlosFragment?.cargarContenidoSegunOpcion(opcionSeleccionada)

                Toast.makeText(this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()

                when (opcionSeleccionada) {


                }

        }*/

        //Implementacion de click listener en un ListView
        lvDatos.setOnItemClickListener(){ parent, view, position, id ->

            Toast.makeText(this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()

            when (position){

                0 -> {replaceFragment(JuanFragment(), "JuanFragment"); spinner.visibility = View.INVISIBLE}
                1 -> {replaceFragment(CarlosFragment(), "CarlosFragment"); spinner.visibility = View.VISIBLE}
                2 -> {replaceFragment(DanielFragment(), "DanielFragment"); spinner.visibility = View.INVISIBLE}
                3 -> {replaceFragment(DiegoFragment(), "DiegoFragment"); spinner.visibility = View.INVISIBLE}
                4 -> {replaceFragment(LuisFragment(), "LuisFragment"); spinner.visibility = View.INVISIBLE}
                5 -> {replaceFragment(ManuelFragment(), "ManuelFragment"); spinner.visibility = View.INVISIBLE}
                6 -> {replaceFragment(ChikenFragment(), "ChikenFragment"); spinner.visibility = View.INVISIBLE}

                else -> {replaceFragment(JuanFragment(), "JuanFragment"); spinner.visibility = View.INVISIBLE}
            }

        }

    }

    /*private fun mostrar(){

        val spinner = findViewById<Spinner>(R.id.spinnerSeries)

        // Obtener el array desde resources
        val opcion = resources.getStringArray(R.array.genero)

        // Configurar el adapter para el Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opcion)

        // Configurar el estilo del dropdown (opcional)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Configurar el adapter en el Spinner
        spinner.adapter = adapter

    }*/

    private fun replaceFragment(fragment: Fragment, tag: String) {
        // Obtener el FragmentManager
        val fragmentManager: FragmentManager = this.supportFragmentManager

        // Iniciar una transacción de fragmentos
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        // Reemplazar el fragment actual con el nuevo fragment
        transaction.replace(R.id.fragmentContainerView, fragment, tag)

        // Agregar la transacción a la pila de retroceso (opcional)
        //transaction.addToBackStack(null)

        // Commit la transacción
        transaction.commit()
    }
}


