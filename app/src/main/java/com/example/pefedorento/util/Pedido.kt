package com.example.pefedorento.util

data class Pedido(
    val idPedido: String,
    val idProduto: String,
    val cpfCliente: String,
    val precoProduto: Double,
    val quantidadeProduto: Int,
){
    constructor() : this("","","",0.0, 0)
}
