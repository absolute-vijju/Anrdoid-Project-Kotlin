package com.example.google_map

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {

            val delhi = LatLng(28.7041, 77.1025)
            val mumbai = LatLng(19.0760, 72.8777)
            val chennai = LatLng(13.0827, 80.2707)
            val kolakata = LatLng(22.5726, 88.3639)
            val ahmedabad = LatLng(23.0225, 72.5714)

            googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID

            googleMap.addPolyline(
                PolylineOptions()
                    .clickable(true)
                    .add(
                        delhi, mumbai, chennai, kolakata, delhi
                    )
                    .width(8f)
                    .color(Color.RED)
            )

            googleMap.addCircle(
                CircleOptions().center(ahmedabad).clickable(true).fillColor(resources.getColor(R.color.map_circle_fill_color)).radius(
                    500.00
                ).strokeColor(resources.getColor(R.color.map_circle_stroke_color)).strokeWidth(6f)
            )

            googleMap.addPolygon(
                PolygonOptions().add(delhi, mumbai, chennai, kolakata).fillColor(
                    resources.getColor(R.color.map_circle_fill_color)
                ).strokeColor(resources.getColor(R.color.map_circle_stroke_color)).strokeWidth(6f)
            )

            googleMap.addMarker(MarkerOptions().position(ahmedabad).title("Ahmedabad"))
            googleMap.addMarker(MarkerOptions().position(delhi).title("Delhi"))
            googleMap.addMarker(MarkerOptions().position(mumbai).title("Mumbai"))
            googleMap.addMarker(MarkerOptions().position(chennai).title("Chennai"))
            googleMap.addMarker(MarkerOptions().position(kolakata).title("Kolkata"))

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ahmedabad, 10f))


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

    }

}
