package com.workly.cliente.data.repository

import com.workly.cliente.data.local.ClienteDao
import com.workly.cliente.data.model.Cliente
import kotlinx.coroutines.flow.Flow

class ClienteRepository(private val clienteDao: ClienteDao) {

    fun listarTodos(): Flow<List<Cliente>> = clienteDao.listarTodos()

    suspend fun inserir(cliente: Cliente) {
        clienteDao.inserir(cliente)
    }

    suspend fun atualizar(cliente: Cliente) {
        clienteDao.atualizar(cliente)
    }

    suspend fun deletar(cliente: Cliente) {
        clienteDao.deletar(cliente)
    }

    suspend fun buscarPorId(id: Int): Cliente? {
        return clienteDao.buscarPorId(id)
    }
}