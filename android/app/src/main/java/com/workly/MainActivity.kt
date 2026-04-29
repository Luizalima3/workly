package com.workly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.workly.cliente.ui.ClienteFormScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ClienteFormScreen(
                onSalvar = { cliente ->
                    println(cliente)
                }
            )
        }
    }
}