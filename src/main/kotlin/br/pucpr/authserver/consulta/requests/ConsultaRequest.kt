package br.pucpr.authserver.consulta.requests

import br.pucpr.authserver.medico.Medico
import br.pucpr.authserver.paciente.Paciente
import jakarta.persistence.Column
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
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
