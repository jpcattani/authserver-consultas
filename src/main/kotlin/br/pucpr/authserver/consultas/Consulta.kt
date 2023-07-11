package br.pucpr.authserver.consultas

import br.pucpr.authserver.consultas.response.ConsultaResponse
import br.pucpr.authserver.medicos.Medico
import br.pucpr.authserver.medicos.response.MedicoResponse
import br.pucpr.authserver.pacientes.Paciente
import br.pucpr.authserver.pacientes.response.PacienteResponse
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "TblConsulta")
data class Consulta(
    @Id @GeneratedValue
    val id: Long = 0,

    @Column(nullable = false)
    val dataConsulta: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "idMedico")
    val medico: Medico,

    @ManyToOne
    @JoinColumn(name = "idPaciente")
    val paciente: Paciente
){
    fun toResponse() = ConsultaResponse(
        id,
        dataConsulta,
        medico = this.medico.toResponse(),
        paciente = this.paciente.toResponse()
    )

}