package br.pucpr.authserver.consulta

import br.pucpr.authserver.users.Role
import br.pucpr.authserver.users.responses.UserResponse
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "TblConsulta")
data class Consulta(
    @Id @GeneratedValue
    val id: Long = 0,

    val data: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "idMedico")
    val medico: Medico,

    @ManyToOne
    @JoinColumn(name = "idPaciente")
    val paciente: Paciente
)