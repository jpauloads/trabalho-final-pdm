package com.example.pefedorento.util

data class Pedido(
    val idPedido: String,
    val idProduto: String,
    val cpfCliente: String,
    val valorTotal: Double,
    val quantidadeProduto: Int,
    val nomeProduto: String,
    val nomeCliente: String,
){
    constructor() : this("","","",0.0, 0, "", "")
}
