package com.example.componentsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.componentsapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.MapView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DanielFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DanielFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daniel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapView = view.findViewById<MapView>(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

    }


    override fun onMapReady(map: GoogleMap) {
        if (map != null) {
            googleMap = map

            // Mover la cámara a una ubicación específica (por ejemplo, latitud y longitud)
            val location = LatLng(37.09024, -95.712891)
            googleMap.addMarker(MarkerOptions().position(location).title("Robert Downey Sr. (2022)"))


            // Agregar más marcadores según sea necesario
            val location1 = LatLng(23.634501, -102.552784)
            googleMap.addMarker(MarkerOptions().position(location1).title("Cartel Land (2015)"))

            val location2 = LatLng(35.86166, 104.195397)
            googleMap.addMarker(MarkerOptions().position(location2).title("Last Train Home (2009)"))

            val location3 = LatLng(-75.250973, -0.071389)
            googleMap.addMarker(MarkerOptions().position(location3).title("Antártida: Un mensaje de otro planeta (2021)"))

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location3, 1f))
        }
    }
}