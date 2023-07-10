package br.pucpr.authserver.medico.response

import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class MedicoResponse (
    val id:Long,
    val nome: String?,
    val crm: String?
)