package com.example.soundvista.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.soundvista.MusicActivity
import com.example.soundvista.R

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

        val button = view.findViewById<Button>(R.id.inicioButton)

        button.setOnClickListener{
            val intent = Intent(activity, MusicActivity::class.java)
            startActivity(intent)
        }
        return view
    }

}