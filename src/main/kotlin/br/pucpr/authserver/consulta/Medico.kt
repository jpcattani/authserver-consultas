package br.pucpr.authserver.consulta

import br.pucpr.authserver.users.responses.UserResponse
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

    @OneToMany(mappedBy = "TblMedico")
    val consultas: Set<Consulta> = emptySet()
)