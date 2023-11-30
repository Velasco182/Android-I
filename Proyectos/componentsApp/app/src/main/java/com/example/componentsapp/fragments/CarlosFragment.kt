package com.example.componentsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.componentsapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CarlosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarlosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var webView: WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_carlos, container, false)

        webView = view.findViewById(R.id.webViewSeries)

        // Habilita la ejecución de scripts en el WebView (opcional, según tus necesidades)
        webView.settings.javaScriptEnabled = true

        // Configura un WebViewClient para manejar las interacciones dentro del WebView
        webView.webViewClient = WebViewClient()

        // Configura un WebChromeClient para manejar eventos como la barra de progreso (opcional)
        webView.webChromeClient = WebChromeClient()

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Carga una URL específica en el WebView
        cargarContenidoSegunOpcion("Terror")
    }

    // Método para cargar contenido en el WebView según la opción seleccionada
    fun cargarContenidoSegunOpcion(opcionSeleccionada: String) {
        // Puedes personalizar el contenido según la opción seleccionada
        val contenidoHTML = when (opcionSeleccionada) {
            "Terror" -> "<html><body><h1>Contenido para Terror</h1></body></html>"
            "Drama" -> "<html><body><h1>Contenido para Drama</h1></body></html>"
            "Triller" -> "<html><body><h1>Contenido para Thriller</h1></body></html>"
            // Agrega más casos según sea necesario
            else -> "<html><body><h1>Contenido por defecto</h1></body></html>"
        }

        webView.loadData(contenidoHTML, "text/html", "UTF-8")
    }

}