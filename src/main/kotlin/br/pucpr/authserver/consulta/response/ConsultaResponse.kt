package br.pucpr.authserver.consulta.response

import br.pucpr.authserver.medico.response.MedicoResponse
import br.pucpr.authserver.paciente.response.PacienteResponse
import java.time.LocalDateTime

data class ConsultaResponse (
    val id: Long,
    val dataConsulta: LocalDateTime,
    val medico: MedicoResponse,
    val paciente: PacienteResponse
)
