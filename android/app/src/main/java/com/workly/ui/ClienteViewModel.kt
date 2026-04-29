package com.workly.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ClienteViewModel : ViewModel() {
    private val _clientes = mutableStateListOf<String>()
    val clientes: List<String> = _clientes

    fun adicionarCliente(nome: String) {
        if (nome.isNotBlank()) {
            _clientes.add(nome)
        }
    }
}