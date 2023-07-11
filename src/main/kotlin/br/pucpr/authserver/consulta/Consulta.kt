package br.pucpr.authserver.consulta

import br.pucpr.authserver.consulta.response.ConsultaResponse
import br.pucpr.authserver.medico.Medico
import br.pucpr.authserver.medico.response.MedicoResponse
import br.pucpr.authserver.paciente.Paciente
import br.pucpr.authserver.paciente.response.PacienteResponse
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
    fun toResponse() = ConsultaResponse(id!!,
        dataConsulta,
        medico = MedicoResponse(medico.id,medico.nome,medico.crm),
        paciente = PacienteResponse(paciente.id,paciente.nome,paciente.telefone),
    )

}