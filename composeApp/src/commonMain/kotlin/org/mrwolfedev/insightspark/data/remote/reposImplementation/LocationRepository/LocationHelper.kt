package org.mrwolfedev.insightspark.data.remote.reposImplementation.LocationRepository//package com.wolf.news.Data.reposImplementation.LocationRepository
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.location.Geocoder
//import android.location.LocationManager
//import com.wolf.news.domain.repository.Location.LocationProvider
//import dagger.hilt.android.qualifiers.ApplicationContext
//import java.util.Locale
//import javax.inject.Inject
//import javax.inject.Singleton
//@Singleton
//class LocationHelper @Inject constructor(
//    @ApplicationContext private val context: Context
//) : LocationProvider {
//
//    @SuppressLint("MissingPermission")
//    override suspend fun getCountryCode(): String? {
//        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        val provider = LocationManager.GPS_PROVIDER
//        val location = locationManager.getLastKnownLocation(provider)
//        return if (location != null) {
//            val geocoder = Geocoder(context, Locale.getDefault())
//            val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
//            addresses?.firstOrNull()?.countryCode
//        } else null
//    }
//}