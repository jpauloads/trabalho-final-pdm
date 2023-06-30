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
import com.example.pefedorento.util.Cliente
import com.example.pefedorento.util.ViewModelCompartilhada

@Composable
fun GetDataScreen(
    navController: NavController,
    viewModelCompartilhada: ViewModelCompartilhada
) {
    var cpf: String by remember { mutableStateOf("") }
    var nome: String by remember { mutableStateOf("") }
    var telefone: String by remember { mutableStateOf("") }
    var endereco: String by remember { mutableStateOf("") }
    var instagram: String by remember { mutableStateOf("") }
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
                text = "Buscar o cliente",
                Modifier.fillMaxWidth(),
                fontSize = 28.sp,
            )
            //CPF
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.6f),
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
                //botão buscar
                Button(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(100.dp),
                    onClick = { /* TODO */
                        viewModelCompartilhada.recuperarCliente(
                            cpf = cpf,
                            context = context
                        ) { cliente ->
                            cpf = cliente.cpf
                            nome = cliente.nome
                            telefone = cliente.telefone
                            endereco = cliente.endereco
                            instagram = cliente.instagram
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
            //botão deletar
            Button(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                onClick = {
                    viewModelCompartilhada.deletarCliente(
                        cpf = cpf,
                        context = context,
                        navController = navController
                    )
                }
            ) {
                Text(text = "Deletar Cliente")
            }
            Spacer(modifier = Modifier)
        }
    }
}