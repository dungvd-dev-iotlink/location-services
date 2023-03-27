package com.example.locations

import android.location.Location

interface MFLocationListener {
  fun onLocationChanged(location: Location?)
}