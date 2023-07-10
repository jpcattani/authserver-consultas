package br.pucpr.authserver.paciente

import br.pucpr.authserver.consulta.Consulta
import br.pucpr.authserver.paciente.response.PacienteResponse
import br.pucpr.authserver.users.responses.UserResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDate


@Entity
@Table(name = "TblPaciente")
data class Paciente(
    @Id @GeneratedValue
    val id: Long = 0,

    @Column(nullable = false)
    val nome: String,

    @Column(nullable = false)
    val dataNascimento: LocalDate,

    @OneToMany(mappedBy = "TblPaciente")
    val consultas: Set<Consulta> = emptySet()
){
    fun toResponse() = PacienteResponse(id!!, nome, dataNascimento)
}