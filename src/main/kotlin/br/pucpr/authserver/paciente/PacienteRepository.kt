package br.pucpr.authserver.paciente

import org.springframework.data.jpa.repository.JpaRepository

interface PacienteRepository : JpaRepository<Paciente, Long> {

}