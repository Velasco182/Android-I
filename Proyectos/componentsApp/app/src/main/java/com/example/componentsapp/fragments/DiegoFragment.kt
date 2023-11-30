package com.example.componentsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.componentsapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DiegoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiegoFragment : Fragment() {

    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diego, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.webView)

        // Habilita la ejecución de scripts en el WebView (opcional, según tus necesidades)
        webView.settings.javaScriptEnabled = true

        // Configura un WebViewClient para manejar las interacciones dentro del WebView
        webView.webViewClient = WebViewClient()

        // Configura un WebChromeClient para manejar eventos como la barra de progreso (opcional)
        webView.webChromeClient = WebChromeClient()

        // Carga una URL específica en el WebView
        webView.loadUrl("https://librefutboltv.com/ver-online/")
    }
}