package com.example.demo

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.locations.MFLocationEngine
import com.example.locations.MFLocationEngineProvider
import com.example.locations.MFLocationListener

class MainActivity : AppCompatActivity() {

  private lateinit var locationEngine: MFLocationEngine
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    locationEngine = MFLocationEngineProvider.getBestLocationEngine(this)
    locationEngine.setLocationChangedListener(object : MFLocationListener {
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