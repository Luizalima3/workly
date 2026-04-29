package com.workly.cliente

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.workly.cliente.model.Cliente

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClienteListScreen(
    viewModel: ClienteViewModel,
    onNovoCliente: () -> Unit
) {
    val clientes = viewModel.clientes

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Clientes") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNovoCliente) {
                Icon(Icons.Default.Add, contentDescription = "Novo cliente")
            }
        }
    ) { paddingValues ->
        if (clientes.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Nenhum cliente cadastrado.")
                Text("Toque no botão + para adicionar um novo cliente.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(clientes) { cliente ->
                    ClienteItem(cliente)
                }
            }
        }
    }
}

@Composable
fun ClienteItem(cliente: Cliente) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = cliente.nome)
            Text(text = cliente.email)
            Text(text = cliente.telefone)
        }
    }
}