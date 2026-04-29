package com.workly.cliente.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.workly.cliente.data.model.Cliente
import com.workly.cliente.data.repository.ClienteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ClienteViewModel(
    private val repository: ClienteRepository
) : ViewModel() {

    val clientes = repository.listarTodos().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun salvar(cliente: Cliente) {
        viewModelScope.launch {
            if (cliente.id == 0) {
                repository.inserir(cliente)
            } else {
                repository.atualizar(cliente)
            }
        }
    }

    fun deletar(cliente: Cliente) {
        viewModelScope.launch {
            repository.deletar(cliente)
        }
    }
}