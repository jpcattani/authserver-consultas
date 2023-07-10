package br.pucpr.authserver.medico.request

import jakarta.validation.constraints.NotBlank

data class MedicoRequest (
    @field:NotBlank
    val nome: String?,

    @field:NotBlank
    val crm: String?
)

