package br.pucpr.authserver.consulta

import br.pucpr.authserver.medico.Medico
import br.pucpr.authserver.paciente.Paciente
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
){}