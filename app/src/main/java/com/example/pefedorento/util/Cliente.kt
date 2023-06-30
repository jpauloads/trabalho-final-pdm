package com.example.pefedorento.util

data class Cliente(
    val cpf: String,
    val nome: String,
    val telefone: String,
    val endereco: String,
    val instagram: String
){
    constructor() : this("","","","", "")
}
