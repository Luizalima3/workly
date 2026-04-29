package com.workly.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ClienteFormScreen(
    viewModel: ClienteViewModel,
    onSalvar: () -> Unit
) {
    val nome = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Cadastro de Cliente")

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = nome.value,
            onValueChange = { nome.value = it },
            label = { Text("Nome") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.adicionarCliente(nome.value)
                onSalvar()
            }
        ) {
            Text("Salvar")
        }
    }
}