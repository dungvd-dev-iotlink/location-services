package com.example.locations

import android.Manifest.permission
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import java.lang.reflect.Method

class MFFusedLocationProvider: MFLocationEngine {

  companion object {
    private const val LOCATION_REQUEST_FASTEST_INTERVAL: Long = 500
    private const val LOCATION_REQUEST_MAX_WAIT_TIME: Long = 0
    private const val TIME_REQUEST_UPDATE_LOCATION: Long = 1000L
  }

  private var fusedLocationProviderClient: Object? = null
  private lateinit var locationRequest: LocationRequest
  private lateinit var locationCallback: LocationCallback

  private var requestLocationUpdatesCallback: Method? = null
  private var removeLocationUpdatesCallback: Method? = null

  constructor(context: Context) : super(context) {
    Log.e("duydung", "init FusedLocationProvider")
    if (fusedLocationProviderClient == null) {
      initFusedLocationComponents(context)
    }
  }

  private fun initFusedLocationComponents(context: Context) {
    val classFusedLocationProviderClient = Class.forName("com.google.android.gms.location.FusedLocationProviderClient")
    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context) as Object

    requestLocationUpdatesCallback = classFusedLocationProviderClient.getMethod("requestLocationUpdates", LocationRequest::class.java, LocationCallback::class.java, Looper::class.java)
    removeLocationUpdatesCallback = classFusedLocationProviderClient.getMethod("removeLocationUpdates", LocationCallback::class.java)

    locationRequest = LocationRequest.create()
      .setInterval(TIME_REQUEST_UPDATE_LOCATION)
      .setFastestInterval(LOCATION_REQUEST_FASTEST_INTERVAL)
      .setMaxWaitTime(LOCATION_REQUEST_MAX_WAIT_TIME)
      .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
    locationCallback = object : LocationCallback() {
      override fun onLocationResult(locationResult: LocationResult) {
        super.onLocationResult(locationResult)
        val currentLocation = locationResult.lastLocation
        lastKnownLocation = currentLocation
        onLocationChangedListener?.onLocationChanged(currentLocation)
      }
    }
  }

  @SuppressLint("MissingPermission")
  override fun startLocationUpdate() {
    val context = contextWeakReference?.get() ?: return

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(
        context,
        permission.ACCESS_FINE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
        context,
        permission.ACCESS_COARSE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED
    ) {
      return
    }

    if (fusedLocationProviderClient == null) {
      initFusedLocationComponents(context)
    }

    try {
      requestLocationUpdatesCallback?.invoke(fusedLocationProviderClient, locationRequest, locationCallback, Looper.getMainLooper())
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  override fun stopLocationUpdate() {
    if (fusedLocationProviderClient != null) {
      try {
        removeLocationUpdatesCallback?.invoke(fusedLocationProviderClient, locationCallback)
        fusedLocationProviderClient = null
        requestLocationUpdatesCallback = null
        removeLocationUpdatesCallback = null
      } catch (e: java.lang.Exception) {
        e.printStackTrace()
      }
    }
  }
}