package com.example.pefedorento.nav

sealed class Screens(val route: String) {
    object MainScreen: Screens(route = "main_screen")

    object TelaClientes: Screens(route = "tela_clientes")
    object TelaProdutos: Screens(route = "tela_produtos")
    object TelaPedidos: Screens(route = "tela_pedidos")

    object GetDataScreen: Screens(route = "get_data_screen")
    object AddDataScreen: Screens(route = "add_data_screen")

    object TelaObterProduto: Screens(route = "tela_obter_produto")
    object TelaAdicionarProduto: Screens(route = "tela_adicionar_produto")

    object TelaObterPedido: Screens(route = "tela_obter_pedido")
    object TelaAdicionarPedido: Screens(route = "tela_adicionar_pedido")
}

