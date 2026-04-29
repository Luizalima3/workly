package com.workly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.workly.navigation.AppNavHost
import com.workly.ui.theme.WorklyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorklyTheme {
                AppNavHost()
            }
        }
    }
}