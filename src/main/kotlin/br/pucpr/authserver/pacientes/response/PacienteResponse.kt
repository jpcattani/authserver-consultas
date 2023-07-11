package br.pucpr.authserver.pacientes.response

data class PacienteResponse (
    val id: Long,
    val nome: String,
    val telefone: String
)