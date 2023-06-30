package com.example.pefedorento.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pefedorento.util.Produto
import com.example.pefedorento.util.ViewModelCompartilhada

@Composable
fun TelaAdicionarProduto(
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

    //Layout da tela de adicionar
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

        //Layout adicionar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Spacer(modifier = Modifier)
            Text(
                text = "Adicionando produto",
                Modifier.fillMaxWidth(),
                fontSize = 28.sp,
            )
            //CPF
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
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
                    if (precoString.isNotEmpty()) {
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
                    if (quantidadeString.isNotEmpty()) {
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
            //Descrição
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
            //botão salvar
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val dadosProduto = Produto(
                        id = id,
                        nome = nome,
                        preco = preco.toDouble(),
                        descricao = descricao,
                        quantidade = quantidade.toInt()
                    )
                    viewModelCompartilhada.salvarProduto(produto = dadosProduto, context = context)
                }
            ) {
                Text(text = "Salvar")
            }
            Spacer(modifier = Modifier)
        }
    }
}