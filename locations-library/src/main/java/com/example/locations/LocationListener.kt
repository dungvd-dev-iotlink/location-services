package com.example.locations

import android.location.Location

interface LocationListener {
  fun onLocationChanged(location: Location?)
}