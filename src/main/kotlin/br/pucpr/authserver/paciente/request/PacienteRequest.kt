package br.pucpr.authserver.paciente.request

import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

class PacienteRequest (
    @field:NotBlank
    val nome: String?,

    @field:NotBlank
    val telefone: String?
)