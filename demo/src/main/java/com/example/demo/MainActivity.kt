package com.example.demo

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.locations.LocationEngine
import com.example.locations.LocationEngineProvider
import com.example.locations.LocationListener

class MainActivity : AppCompatActivity() {

  private lateinit var locationEngine: LocationEngine
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    locationEngine = LocationEngineProvider.getBestLocationEngine(this)
    locationEngine.setLocationChangedListener(object : LocationListener {
      override fun onLocationChanged(location: Location?) {
        Log.e("duydung", "Location change: Lat ${location?.latitude} va Long ${location?.longitude}")
      }
    })
    locationEngine.startLocationUpdate()
  }

  override fun onStop() {
    locationEngine.stopLocationUpdate()
    super.onStop()
  }
}