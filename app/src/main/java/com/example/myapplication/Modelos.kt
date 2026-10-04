package com.example.myapplication

data class Estabelecimento(
    val id: Int,
    val nome: String,
    val categoria: String,
    val nota: String,
    val endereco: String
)

data class Agendamento(
    val id: Int,
    val idEstabelecimento: Int,
    val servico: String,
    val data: String,
    val hora: String,
    val preco: Double,
    val observacao: String = "",
    val concluido: Boolean = false
)

data class Notificacao(
    val id: Int,
    val titulo: String,
    val mensagem: String,
    val quando: String,
    val lida: Boolean = false
)
