package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.locations.LocationProvider

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val locationProvider = LocationProvider()
    val location = locationProvider.getLocation()
    Toast.makeText(this, "Thong tin location la: ${location.latitude} va ${location.longitude}", Toast.LENGTH_SHORT).show()
  }
}