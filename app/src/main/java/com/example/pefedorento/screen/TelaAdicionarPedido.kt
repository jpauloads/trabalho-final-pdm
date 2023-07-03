package com.example.pefedorento.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pefedorento.util.Pedido
import com.example.pefedorento.util.Produto
import com.example.pefedorento.util.ViewModelCompartilhada

@Composable
fun TelaAdicionarPedido(
    navController: NavController,
    viewModelCompartilhada: ViewModelCompartilhada
) {

    /*TODO: REFAZER essa tela*/

    var idPedido: String by remember { mutableStateOf("") }
    var idProduto: String by remember { mutableStateOf("") }
    var nomeProduto: String by remember { mutableStateOf("") }
    var precoProduto: Double by remember { mutableStateOf(0.0) }

    var cpfCliente: String by remember { mutableStateOf("") }
    var nomeCliente: String by remember { mutableStateOf("") }

    var quantidadeProduto: Int by remember { mutableStateOf(0) }
    var quantidadeProdutoString: String by remember { mutableStateOf("") }

    val context = LocalContext.current

    //Layout da tela de adicionar pedido
    Column(modifier = Modifier.fillMaxSize()) {
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

        //Layout adicionar pedido
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Spacer(modifier = Modifier)
            Text(
                text = "Novo pedido",
                Modifier.fillMaxWidth(),
                fontSize = 28.sp,
            )

            //id do pedido
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = idPedido,
                onValueChange = {
                    idPedido = it
                },
                label = {
                    Text(text = "Id do pedido")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                )
            )

            //selecionando o cliente (pelo cpf) e verificando se existe
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.6f),
                    value = cpfCliente,
                    onValueChange = {
                        cpfCliente = it
                    },
                    label = {
                        Text(text = "CPF do cliente")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    )
                )
                //botão buscar
                Button(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(100.dp),
                    onClick = { /* TODO */
                        viewModelCompartilhada.recuperarCliente(
                            cpf = cpfCliente,
                            context = context
                        ) { cliente ->
                            cpfCliente = cliente.cpf
                            nomeCliente = cliente.nome
                        }
                    }
                ) {
                    Text(text = "Buscar Cliente")
                }
            }
            Text(text = "Nome do cliente: " + nomeCliente)

            //escolhendo o produto (pelo id) e verificando se existe
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.6f),
                    value = idProduto,
                    onValueChange = {
                        idProduto = it
                    },
                    label = {
                        Text(text = "Id do produto")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    )
                )
                //botão buscar
                Button(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(100.dp),
                    onClick = { /* TODO */
                        viewModelCompartilhada.recuperarProduto(
                            id = idProduto,
                            context = context
                        ) { produto ->
                            idProduto = produto.id
                            nomeProduto = produto.nome
                            precoProduto = produto.preco
                        }
                    }
                ) {
                    Text(text = "Buscar produto")
                }
            }
            Text(text = "Nome do produto: " + nomeProduto)
            Text(text = "Preço: " + precoProduto.toString())


            //Quantidade
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = quantidadeProdutoString,
                onValueChange = {
                    quantidadeProdutoString = it
                    if (quantidadeProdutoString.isNotEmpty()) {
                        quantidadeProduto = quantidadeProdutoString.toInt()
                    }
                },
                label = {
                    Text(text = "Quantidade")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                )
            )

            //botão salvar
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val pedidoNovo = Pedido(
                        idPedido = idPedido,
                        idProduto = idProduto,
                        cpfCliente = cpfCliente,
                        valorTotal = (quantidadeProduto * precoProduto).toDouble(),
                        nomeProduto = nomeProduto,
                        nomeCliente = nomeCliente,
                        quantidadeProduto = quantidadeProduto.toInt()
                    )
                    viewModelCompartilhada.salvarPedido(pedido = pedidoNovo, context = context)
                }
            ) {
                Text(text = "Novo Pedido")
            }
            Spacer(modifier = Modifier)
        }
    }
}