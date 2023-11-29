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
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        if (map != null) {
            googleMap = map
            // Mover la cámara a una ubicación específica (por ejemplo, latitud y longitud)
            val location = LatLng(-34.0, 151.0)
            googleMap.addMarker(MarkerOptions().position(location).title("Marcador 1"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))

            // Agregar más marcadores según sea necesario
            val location2 = LatLng(-33.0, 150.0)
            googleMap.addMarker(MarkerOptions().position(location2).title("Marcador 2"))
        }
    }
}