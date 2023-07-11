package br.pucpr.authserver.pacientes

import br.pucpr.authserver.consultas.Consulta
import br.pucpr.authserver.pacientes.response.PacienteResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table


@Entity
@Table(name = "TblPaciente")
data class Paciente(
    @Id @GeneratedValue
    val id: Long = 0,

    @Column(nullable = false)
    val nome: String,

    @Column(nullable = false)
    val telefone: String,

    @OneToMany(mappedBy = "paciente")
    val consultas: Set<Consulta> = emptySet()
){
    fun toResponse() = PacienteResponse(id, nome, telefone)
}