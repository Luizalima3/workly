package com.workly.cliente

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.workly.cliente.model.Cliente

class ClienteViewModel : ViewModel() {

    private val _clientes = mutableStateListOf<Cliente>()
    val clientes: List<Cliente> = _clientes

    fun adicionarCliente(cliente: Cliente) {
        _clientes.add(cliente)
    }
}