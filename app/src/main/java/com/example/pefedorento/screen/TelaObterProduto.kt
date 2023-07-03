package com.example.pefedorento.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import com.example.pefedorento.ui.theme.Purple200
import com.example.pefedorento.util.Produto
import com.example.pefedorento.util.ViewModelCompartilhada

@Composable
fun TelaObterProduto(
    navController: NavController,
    viewModelCompartilhada: ViewModelCompartilhada
) {
    var id: String by remember { mutableStateOf("") }
    var nome: String by remember { mutableStateOf("") }
    var preco: Double by remember { mutableStateOf(0.0) }
    var precoString: String by remember { mutableStateOf("") }
    var descricao: String by remember { mutableStateOf("") }
    var quantidade: Int by remember { mutableStateOf(0) }
    var quantidadeString: String by remember { mutableStateOf("") }

    val context = LocalContext.current

    //tela de busca
    Column(
        modifier = Modifier
            .fillMaxSize(),
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

        //Layout tela de busca
        Column(
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier)
            Text(
                text = "Buscar o produto",
                Modifier.fillMaxWidth(),
                fontSize = 28.sp,
                color = Purple200
            )
            //id
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.6f),
                    value = id,
                    onValueChange = {
                        id = it
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
                            id = id,
                            context = context
                        ) { produto ->
                            id = produto.id
                            nome = produto.nome
                            precoString = produto.preco.toString()
                            preco = precoString.toDouble()
                            quantidadeString = produto.quantidade.toString()
                            quantidade = quantidadeString.toInt()
                            descricao = produto.descricao
                        }
                    }
                ) {
                    Text(text = "Buscar")
                }
            }

            //nome
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = nome,
                onValueChange = {
                    nome = it
                },
                label = {
                    Text(text = "Nome")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                )
            )
            //Preço
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = precoString,
                onValueChange = {
                    precoString = it
                    if(precoString.isNotEmpty()) {
                        preco = precoString.toDouble()
                    }
                },
                label = {
                    Text(text = "Preço")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next,
                )
            )
            //Quantidade
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = quantidadeString,
                onValueChange = {
                    quantidadeString = it
                    if(quantidadeString.isNotEmpty()) {
                        quantidade = quantidadeString.toInt()
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
            //descrição
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = descricao,
                onValueChange = {
                    descricao = it
                },
                label = {
                    Text(text = "Descrição")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                )
            )
            // botão salvar
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                onClick = {
                    val produto = Produto(
                        id = id,
                        nome = nome,
                        preco = precoString.toDouble(),
                        quantidade = quantidadeString.toInt(),
                        descricao = descricao,
                    )
                    viewModelCompartilhada.salvarProduto(produto = produto, context = context)
                }
            ) {
                Text(text = "Salvar alterações")
            }
            //botão deletar
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                onClick = {
                    viewModelCompartilhada.deletarProduto(
                        id = id,
                        context = context,
                        navController = navController
                    )
                }
            ) {
                Text(text = "Deletar Produto")
            }
            Spacer(modifier = Modifier)
        }
    }
}