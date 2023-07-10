package br.pucpr.authserver.medico

import br.pucpr.authserver.consulta.Consulta
import br.pucpr.authserver.medico.response.MedicoResponse
import br.pucpr.authserver.paciente.response.PacienteResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "TblMedico")
data class Medico(
    @Id @GeneratedValue
    val id: Long = 0,

    @Column(nullable = false)
    val nome: String,

    @Column(nullable = false)
    val crm: String,

    @OneToMany(mappedBy = "TblMedico")
    val consultas: Set<Consulta> = emptySet()
){
    fun toResponse() = MedicoResponse(id!!, nome, crm)

}