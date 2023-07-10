package br.pucpr.authserver.consulta.requests

import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

data class ConsultaRequest (
    @field:NotBlank
    val dataConsulta: LocalDateTime,
    @field:NotBlank
    val idMedico: Long,
    @field:NotBlank
    val idPaciente: Long
    ){}
