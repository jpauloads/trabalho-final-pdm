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
import com.example.pefedorento.util.Cliente
import com.example.pefedorento.util.ViewModelCompartilhada

@Composable
fun AddDataScreen(
    navController: NavController,
    viewModelCompartilhada: ViewModelCompartilhada
) {
    var cpf: String by remember { mutableStateOf("") }
    var nome: String by remember { mutableStateOf("") }
    var telefone: String by remember { mutableStateOf("") }
    var endereco: String by remember { mutableStateOf("") }
    var instagram: String by remember { mutableStateOf("") }

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
                text = "Adicionando cliente",
                Modifier.fillMaxWidth(),
                fontSize = 28.sp,
            )
            //CPF
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = cpf,
                onValueChange = {
                    cpf = it
                },
                label = {
                    Text(text = "Cpf")
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
            //Telefone
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = telefone,
                onValueChange = {
                    telefone = it
                },
                label = {
                    Text(text = "Telefone")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                )
            )
            //endereço
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = endereco,
                onValueChange = {
                    endereco = it
                },
                label = {
                    Text(text = "Endereço")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                )
            )
            //instagram
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = instagram,
                onValueChange = {
                    instagram = it
                },
                label = {
                    Text(text = "Instagram")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                )
            )
            //botão salvar
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val dadosCliente = Cliente(
                        cpf = cpf,
                        nome = nome,
                        telefone = telefone,
                        endereco = endereco,
                        instagram = instagram
                    )
                    viewModelCompartilhada.salvarCliente(cliente = dadosCliente, context = context)
                }
            ) {
                Text(text = "Salvar")
            }
            Spacer(modifier = Modifier)
        }
    }
}
