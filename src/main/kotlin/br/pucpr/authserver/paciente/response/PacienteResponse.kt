package br.pucpr.authserver.paciente.response

import java.time.LocalDate

data class PacienteResponse (
    val id: Long,
    val nome: String,
    val telefone: String
)