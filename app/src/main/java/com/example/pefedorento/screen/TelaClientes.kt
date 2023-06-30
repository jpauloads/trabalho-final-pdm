package com.example.pefedorento.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pefedorento.nav.Screens
import com.example.pefedorento.ui.theme.Purple200

@Composable
fun TelaClientes(
    navController: NavController,
//    viewModelCompartilhada: ViewModelCompartilhada
) {
    Column(
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Clientes",
            Modifier.fillMaxWidth(),
            fontSize = 28.sp,
            color = Purple200,
        )
        //Buscar cliente
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(route = Screens.GetDataScreen.route)
            }
        ) {
            Text(text = "Buscar dados do cliente")
        }
        //Adicionar cliente
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(route = Screens.AddDataScreen.route)
            }
        ) {
            Text(text = "Adicionar cliente")
        }
    }
}