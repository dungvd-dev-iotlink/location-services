package com.example.locations

import android.content.Context
import android.location.Location
import java.lang.ref.WeakReference

abstract class MFLocationEngine {

  protected var lastKnownLocation: Location? = null
  protected var contextWeakReference: WeakReference<Context>? = null
  protected var onLocationChangedListener: MFLocationListener? = null

  protected constructor(context: Context) {
    contextWeakReference = WeakReference(context)
  }

  fun setLocationChangedListener(locationListener: MFLocationListener?) {
    onLocationChangedListener = locationListener
  }
  abstract fun startLocationUpdate()

  abstract fun stopLocationUpdate()
}