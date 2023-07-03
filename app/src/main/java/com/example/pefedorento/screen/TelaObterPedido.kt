package com.example.pefedorento.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pefedorento.ui.theme.Purple200
import com.example.pefedorento.util.Pedido
import com.example.pefedorento.util.ViewModelCompartilhada

@Composable
fun TelaObterPedido(
    navController: NavController,
    viewModelCompartilhada: ViewModelCompartilhada
) {

    var cpf: String by remember { mutableStateOf("") }
    val pedidosPorCliente by viewModelCompartilhada.pedidosPorCliente.collectAsState()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // botao de voltar
        Row(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "botao_voltar")
            }
        }

        //Layout tela de buscar pedidos
        Column(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier)
            Text(
                text = "Buscar os pedidos",
                Modifier.fillMaxWidth(),
                fontSize = 28.sp,
                color = Purple200
            )
            //buscar pelo cpf do cliente
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = cpf,
                onValueChange = {
                    cpf = it
                },
                label = {
                    Text(text = "CPF do cliente")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
//                        imeAction = ImeAction.Next,
                )
            )
            //botão buscar
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    viewModelCompartilhada.recuperarClientesPorPedido(
                        cpf = cpf,
                        context = context
                    )
                }
            ) {
                Text(text = "Buscar Cliente")
            }
            Spacer(modifier = Modifier)

            if (pedidosPorCliente.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(pedidosPorCliente) { pedido ->
                        PedidoItem(pedido)
                        Divider(color = Color.Gray, thickness = 1.dp)
                    }
                }
            } else {
                Text(text = "Nenhum pedido encontrado.")
            }
        }

    }
}


@Composable
fun PedidoItem(pedido: Pedido) {
    // Layout do item do pedido
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Número do Pedido: ${pedido.idPedido}",
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            Text(text = "CPF do cliente: ${pedido.cpfCliente}")
            Text(text = "Nome do cliente: ${pedido.nomeCliente}")
            Text(text = "Nome do produto: ${pedido.nomeProduto}")
            Text(text = "Quantidade de produtos: ${pedido.quantidadeProduto}")
            Text(text = "Total: R$ ${pedido.valorTotal}")
        }
    }
}