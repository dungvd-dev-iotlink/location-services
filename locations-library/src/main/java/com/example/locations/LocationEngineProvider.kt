package com.example.locations

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

object LocationEngineProvider {

  private const val GOOGLE_LOCATION_SERVICES = "com.google.android.gms.location.LocationServices"
  private const val GOOGLE_API_AVAILABILITY = "com.google.android.gms.common.GoogleApiAvailability"

  fun getBestLocationEngine(context: Context): LocationEngine {
    var hasGoogleLocationServices = isOnClasspath(GOOGLE_LOCATION_SERVICES)
    if (isOnClasspath(GOOGLE_API_AVAILABILITY)) {
      hasGoogleLocationServices = hasGoogleLocationServices and (GoogleApiAvailability.getInstance()
        .isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS)
    }
    return if (hasGoogleLocationServices) FusedLocationProvider(context) else DeviceLocationProvider(context)
  }

  private fun isOnClasspath(className: String): Boolean {
    var isOnClassPath = true
    try {
      Class.forName(className)
    } catch (exception: ClassNotFoundException) {
      isOnClassPath = false
    }
    return isOnClassPath
  }
}