package com.example.locations

import android.content.Context
import android.util.Log

class DeviceLocationProvider: LocationEngine {

  constructor(context: Context): super(context) {
    Log.e("duydung", "init DeviceLocationProvider")
  }

  override fun startLocationUpdate() {

  }

  override fun stopLocationUpdate() {

  }
}