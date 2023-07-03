package com.example.pefedorento.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pefedorento.screen.*
import com.example.pefedorento.util.ViewModelCompartilhada

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModelCompartilhada: ViewModelCompartilhada
) {
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route
    ) {
        //tela principal
        composable(
            route = Screens.MainScreen.route
        ) {
            MainScreen(
                navController = navController,
            )
        }
        // TELA CLIENTE
        composable(
            route = Screens.TelaClientes.route
        ) {
            TelaClientes(
                navController = navController,
//                viewModelCompartilhada = viewModelCompartilhada
            )
        }
        // TELA PRODUTO
        composable(
            route = Screens.TelaProdutos.route
        ) {
            TelaProdutos(
                navController = navController,
//                viewModelCompartilhada = viewModelCompartilhada
            )
        }
        // TELA PEDIDO
        composable(
            route = Screens.TelaPedidos.route
        ) {
            TelaPedidos(
                navController = navController,
//                viewModelCompartilhada = viewModelCompartilhada
            )
        }
        //tela busca cliente
        composable(
            route = Screens.GetDataScreen.route
        ) {
            GetDataScreen(
                navController = navController,
                viewModelCompartilhada = viewModelCompartilhada
            )
        }
        //tela add cliente
        composable(
            route = Screens.AddDataScreen.route
        ) {
            AddDataScreen(
                navController = navController,
                viewModelCompartilhada = viewModelCompartilhada
            )
        }

        //tela busca produto
        composable(
            route = Screens.TelaObterProduto.route
        ) {
            TelaObterProduto(
                navController = navController,
                viewModelCompartilhada = viewModelCompartilhada
            )
        }

        //tela adicionar produto
        composable(
            route = Screens.TelaAdicionarProduto.route
        ) {
            TelaAdicionarProduto(
                navController = navController,
                viewModelCompartilhada = viewModelCompartilhada
            )
        }

        //tela busca PEDIDO
        composable(
            route = Screens.TelaObterPedido.route
        ) {
            TelaObterPedido(
                navController = navController,
                viewModelCompartilhada = viewModelCompartilhada
            )
        }

        //tela adicionar PEDIDO
        composable(
            route = Screens.TelaAdicionarPedido.route
        ) {
            TelaAdicionarPedido(
                navController = navController,
                viewModelCompartilhada = viewModelCompartilhada
            )
        }
    }
}