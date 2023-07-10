package br.pucpr.authserver.medico

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MedicoRepository : JpaRepository<Medico, Long> {

}