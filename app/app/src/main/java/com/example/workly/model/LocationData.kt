package com.example.workly.model

/**
 * Dados de localização do prestador
 * Utilizado para plotagem no mapa - RF02
 */
data class LocationData(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val accuracy: Float = 0f
)

/**
 * Prestador com informações de localização para o mapa
 */
data class ProviderLocationInfo(
    val providerId: String = "",
    val name: String = "",
    val specialty: String = "",
    val rating: Float = 0f,
    val profileImageUrl: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val distance: Float = 0f // em metros
)
