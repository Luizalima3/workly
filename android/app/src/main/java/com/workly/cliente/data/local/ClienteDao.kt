package com.workly.cliente.data.local

import androidx.room.*
import com.seupacote.cliente.data.model.Cliente
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {

    @Insert
    suspend fun inserir(cliente: Cliente)

    @Update
    suspend fun atualizar(cliente: Cliente)

    @Delete
    suspend fun deletar(cliente: Cliente)

    @Query("SELECT * FROM clientes ORDER BY nome ASC")
    fun listarTodos(): Flow<List<Cliente>>

    @Query("SELECT * FROM clientes WHERE id = :id")
    suspend fun buscarPorId(id: Int): Cliente?
}