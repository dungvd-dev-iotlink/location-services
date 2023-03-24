package com.example.locations

import android.location.Location

class LocationProvider {
  fun getLocation(): Location {
    val location = Location("dummy")
    location.latitude = 16.0
    location.longitude = 108.0
    return location
  }
}