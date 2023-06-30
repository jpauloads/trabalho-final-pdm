package com.example.pefedorento

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pefedorento.nav.NavGraph
import com.example.pefedorento.ui.theme.PeFedorentoTheme
import com.example.pefedorento.util.ViewModelCompartilhada

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val viewModelCompartilhada: ViewModelCompartilhada by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PeFedorentoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    navController = rememberNavController()
                    NavGraph(
                        navController = navController,
                        viewModelCompartilhada = viewModelCompartilhada
                    )
                }
            }
        }
    }
}
