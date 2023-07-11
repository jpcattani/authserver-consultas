package br.pucpr.authserver.consultas.response

import br.pucpr.authserver.medicos.response.MedicoResponse
import br.pucpr.authserver.pacientes.response.PacienteResponse
import java.time.LocalDateTime

data class ConsultaResponse (
    val id: Long,
    val dataConsulta: LocalDateTime,
    val medico: MedicoResponse,
    val paciente: PacienteResponse
)
