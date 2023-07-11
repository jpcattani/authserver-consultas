package br.pucpr.authserver.consultas.requests

import jakarta.validation.constraints.NotBlank
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class ConsultaRequest (
    val dataConsulta: LocalDateTime,
    val idMedico: Long,
    val idPaciente: Long
    ){}
