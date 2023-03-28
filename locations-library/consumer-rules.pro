# Consumer proguard rules for Location Library

# --- GMS ---
-keep class com.google.android.gms.location.LocationServices
-keep class com.google.android.gms.common.GoogleApiAvailability

# FusedLocationProviderClient using reflection so we need to keep it.
-keep class com.google.android.gms.location.FusedLocationProviderClient {*;}
-keep class com.google.android.gms.location.LocationResult {*;}

-dontwarn com.google.android.gms.**

-keep class androidx.collection.LongSparseArray {public *;}