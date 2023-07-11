package br.pucpr.authserver.medicos

import br.pucpr.authserver.consultas.Consulta
import br.pucpr.authserver.medicos.response.MedicoResponse
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

    @OneToMany(mappedBy = "medico")
    val consultas: Set<Consulta> = emptySet()
){
    fun toResponse() = MedicoResponse(id, nome, crm)

}