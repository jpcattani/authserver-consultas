package br.pucpr.authserver.consulta

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

    @OneToMany(mappedBy = "TblPaciente")
    val consultas: Set<Consulta> = emptySet()
)