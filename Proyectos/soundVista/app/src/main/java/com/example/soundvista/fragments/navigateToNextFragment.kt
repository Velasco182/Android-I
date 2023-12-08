package com.example.soundvista.fragments

import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction

object FragmentHelper{
    fun replaceFragment(fragmentManager: FragmentManager, containerId: Int, fragment: Fragment) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        transaction.addToBackStack(null) // Agrega a la pila de retroceso si es necesario
        transaction.commit()
    }
}