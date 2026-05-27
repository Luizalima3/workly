package com.example.workly.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navController: NavController) {
    val center = LatLng(-23.550520, -46.633308)
    var radiusKm by remember { mutableStateOf(3f) }
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.CameraPosition.fromLatLngZoom(center, 12f)
    }
    val providers = remember {
        listOf(
            ProviderLocation("João Eletricista", LatLng(-23.551000, -46.634500), "Eletricista"),
            ProviderLocation("Maria Pintora", LatLng(-23.548200, -46.631000), "Pintor"),
            ProviderLocation("Carlos Encanador", LatLng(-23.556000, -46.638000), "Encanador"),
            ProviderLocation("Renata Limpeza", LatLng(-23.543500, -46.648000), "Serviços gerais")
        )
    }

    val radiusMeters = (radiusKm * 1000).toInt()
    val filteredProviders = providers.filter { provider ->
        haversineDistance(center.latitude, center.longitude, provider.location.latitude, provider.location.longitude) <= radiusKm
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mapa de Prestadores") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = { /* apenas ícone informativo */ }) {
                        Icon(Icons.Default.LocationOn, contentDescription = "Localização")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Raio atual: ${radiusKm.toInt()} km",
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                fontSize = 16.sp
            )
            Slider(
                value = radiusKm,
                onValueChange = { radiusKm = it.coerceIn(1f, 10f) },
                valueRange = 1f..10f,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
                    .padding(16.dp)
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    properties = com.google.maps.android.compose.MapProperties(isMyLocationEnabled = false)
                ) {
                    Circle(
                        center = center,
                        radius = radiusMeters.toDouble(),
                        fillColor = 0x220066CC,
                        strokeColor = 0x660066CC,
                        strokeWidth = 2f
                    )
                    filteredProviders.forEach { provider ->
                        Marker(
                            state = MarkerState(position = provider.location),
                            title = provider.name,
                            snippet = provider.specialty
                        )
                    }
                }
            }
            Text(
                text = "Prestadores no raio: ${filteredProviders.size}",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            filteredProviders.forEach { provider ->
                Text(
                    text = "• ${provider.name} — ${provider.specialty}",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
    }
}

private data class ProviderLocation(
    val name: String,
    val location: LatLng,
    val specialty: String
)

private fun haversineDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Float {
    val earthRadius = 6371.0
    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)
    val a = Math.sin(dLat / 2).pow(2.0) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2).pow(2.0)
    val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
    return (earthRadius * c).toFloat()
}

private fun Double.pow(exponent: Double): Double = kotlin.math.pow(exponent)

@Preview(showBackground = true, heightDp = 900)
@Composable
fun MapScreenPreview() {
    val navController = androidx.navigation.compose.rememberNavController()
    MaterialTheme {
        MapScreen(navController)
    }
}
