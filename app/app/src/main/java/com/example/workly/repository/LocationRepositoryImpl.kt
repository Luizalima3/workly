package com.example.workly.repository

import com.example.workly.service.LocationService
import com.example.workly.service.LocationUpdate
import kotlinx.coroutines.flow.Flow

/**
 * Repositório de Localização - RF02
 * Camada de abstração para gerenciar atualizações de localização
 */
class LocationRepository(private val locationService: LocationService) {

    fun getCurrentLocation(): Flow<LocationUpdate> {
        return locationService.getCurrentLocation()
    }

    suspend fun getLastKnownLocation(): LocationUpdate? {
        return locationService.getLastKnownLocation()
    }
}
