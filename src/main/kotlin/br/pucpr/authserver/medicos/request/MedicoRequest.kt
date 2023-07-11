package br.pucpr.authserver.medicos.request

import jakarta.validation.constraints.NotBlank

data class MedicoRequest (
    @field:NotBlank
    val nome: String?,

    @field:NotBlank
    val crm: String?
)

