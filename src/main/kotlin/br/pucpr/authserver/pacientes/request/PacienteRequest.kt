package br.pucpr.authserver.pacientes.request

import jakarta.validation.constraints.NotBlank

class PacienteRequest (
    @field:NotBlank
    val nome: String?,

    @field:NotBlank
    val telefone: String?
)