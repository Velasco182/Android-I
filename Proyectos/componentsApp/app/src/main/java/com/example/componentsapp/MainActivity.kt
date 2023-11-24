package com.example.componentsapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
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
        val personas = mutableListOf("Juan", "Carlos", "Daniel", "Diego", "Luis", "Manuel", "Chicken Little")
        val lvDatos = findViewById<ListView>(R.id.lvDatos)

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, personas)
        lvDatos.adapter = arrayAdapter

        replaceFragment(JuanFragment())

        //Implementacion de click listener en un ListView
        lvDatos.setOnItemClickListener(){ parent, view, position, id ->

            Toast.makeText(this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()

            when (position){

                0 -> replaceFragment(JuanFragment())
                1 -> replaceFragment(CarlosFragment())
                2 -> replaceFragment(DanielFragment())
                3 -> replaceFragment(DiegoFragment())
                4 -> replaceFragment(LuisFragment())
                5 -> replaceFragment(ManuelFragment())
                6 -> replaceFragment(ChikenFragment())

                else -> replaceFragment(JuanFragment())
            }

        }

    }

    private fun replaceFragment(fragment: Fragment) {
        // Obtener el FragmentManager
        val fragmentManager: FragmentManager = this.supportFragmentManager

        // Iniciar una transacción de fragmentos
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        // Reemplazar el fragment actual con el nuevo fragment
        transaction.replace(R.id.fragmentContainerView, fragment)

        // Agregar la transacción a la pila de retroceso (opcional)
        //transaction.addToBackStack(null)

        // Commit la transacción
        transaction.commit()
    }
}
