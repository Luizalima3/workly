package com.workly.cliente.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.workly.cliente.data.model.Cliente

@Composable
fun ClienteListScreen(
    clientes: List<Cliente>,
    onDelete: (Cliente) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(clientes) { cliente ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = cliente.nome)
                    Text(text = cliente.email)
                    Text(text = cliente.telefone)
                    Text(text = cliente.endereco)

                    Button(onClick = { onDelete(cliente) }) {
                        Text("Excluir")
                    }
                }
            }
        }
    }
}