package com.workly.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class DemandItem(
    val title: String,
    val category: String,
    val budget: String,
    val location: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    userType: String,
    onVerClientesClick: () -> Unit = {}
) {
    val search = remember { mutableStateOf("") }

    val demands = listOf(
        DemandItem("Designer para logo", "Design", "R$ 300", "João Pessoa"),
        DemandItem("Faxina residencial", "Serviços Gerais", "R$ 150", "Campina Grande"),
        DemandItem("Aula particular de matemática", "Educação", "R$ 80", "Remoto")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Workly - $userType")
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Bem-vindo ao Workly",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                OutlinedTextField(
                    value = search.value,
                    onValueChange = { search.value = it },
                    label = { Text("Buscar serviços ou profissionais") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }

            item {
                Button(
                    onClick = onVerClientesClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver clientes")
                }
            }

            item {
                Text(
                    text = "Demandas em destaque",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            items(demands) { demand ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = demand.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Categoria: ${demand.category}")
                        Text("Orçamento: ${demand.budget}")
                        Text("Local: ${demand.location}")
                    }
                }
            }
        }
    }
}