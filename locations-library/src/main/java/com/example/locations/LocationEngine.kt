package com.example.locations

import android.content.Context
import android.location.Location
import java.lang.ref.WeakReference

abstract class LocationEngine {

  protected var lastKnownLocation: Location? = null
  protected var contextWeakReference: WeakReference<Context>? = null
  protected var onLocationChangedListener: LocationListener? = null

  protected constructor(context: Context) {
    contextWeakReference = WeakReference(context)
  }

  fun setLocationChangedListener(locationListener: LocationListener?) {
    onLocationChangedListener = locationListener
  }
  abstract fun startLocationUpdate()

  abstract fun stopLocationUpdate()
}