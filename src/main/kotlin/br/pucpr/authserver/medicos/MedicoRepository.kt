package br.pucpr.authserver.medicos

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MedicoRepository : JpaRepository<Medico, Long> {

}