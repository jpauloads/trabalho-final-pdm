package com.example.pefedorento.util

data class Produto(
    val id: String,
    val nome: String,
    val preco: Double,
    val descricao: String,
    val quantidade: Int
){
    constructor() : this("","",0.0,"",0)
}