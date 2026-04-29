package com.workly.cliente.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seupacote.cliente.data.model.Cliente

@Database(
    entities = [Cliente::class],
    version = 1,
    exportSchema = false
)
abstract class ClienteDatabase : RoomDatabase() {
    abstract fun clienteDao(): ClienteDao
}