package com.example.workly.view

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.example.workly.components.ServiceCard
import com.example.workly.model.ServiceItem
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProviderHomeScreen(navController: NavController) {

    val services = listOf(
        ServiceItem(
            title = "Trocar chuveiro",
            description = "Apartamento com vazamento no banheiro.",
            category = "Encanador",
            buttonText = "Tenho interesse"
        ),
        ServiceItem(
            title = "Pintar parede",
            description = "Sala de estar precisa de pintura rápida.",
            category = "Pintor",
            buttonText = "Tenho interesse"
        ),
        ServiceItem(
            title = "Conserto de pia",
            description = "Cozinha com vazamento frequente.",
            category = "Encanador",
            buttonText = "Tenho interesse"
        ),
        ServiceItem(
            title = "Instalar ventilador",
            description = "Quarto pequeno, só instalação elétrica.",
            category = "Eletricista",
            buttonText = "Tenho interesse"
        )
    )

    val filterCategories = listOf("Todos", "Eletricista", "Encanador", "Pintor")
    var selectedCategory by remember { mutableStateOf("Todos") }
    var query by remember { mutableStateOf("") }

    val filteredServices = services.filter { service ->
        val matchesQuery = service.title.contains(query, ignoreCase = true) ||
                service.description.contains(query, ignoreCase = true) ||
                service.category.contains(query, ignoreCase = true)
        val matchesCategory = selectedCategory == "Todos" || service.category == selectedCategory
        matchesQuery && matchesCategory
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Serviços disponíveis") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("profile") }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu / Perfil")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {

                TextField(
                    value = query,
                    onValueChange = { query = it },
                    label = { Text("Buscar serviços") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    filterCategories.forEach { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            label = { Text(category) }
                        )
                    }
                }

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp)
                ) {
                    items(filteredServices) { service ->
                    ServiceCard(
                        title = service.title,
                        description = service.description,
                        category = service.category,
                        buttonText = service.buttonText,
                        onClick = {
                            navController.navigate(
                                "service_detail/provider/${Uri.encode(service.category)}/${Uri.encode(service.title)}?description=${Uri.encode(service.description)}"
                            )
                        }
                    )
                }
                }
            }
        }
    )
}

@Preview(showBackground = true, heightDp = 1000)
@Composable
fun ProviderHomeScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        ProviderHomeScreen(navController = navController)
    }
}