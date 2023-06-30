package com.example.pefedorento.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pefedorento.nav.Screens
import com.example.pefedorento.ui.theme.Purple200

@Composable
fun MainScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pé Fedorento",
            Modifier.fillMaxWidth()
                .padding(bottom = 50.dp),
            fontSize = 28.sp,
            color = Purple200,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        //Ir para tela clientes
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(route = Screens.TelaClientes.route)
            }
        ) {
            Text(text = "Clientes")
        }

        //Ir para tela produtos
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(route = Screens.TelaProdutos.route)
            }
        ) {
            Text(text = "Produtos")
        }

        //Ir para tela pedidos
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(route = Screens.TelaPedidos.route)
            }
        ) {
            Text(text = "Pedidos")
        }
    }
}