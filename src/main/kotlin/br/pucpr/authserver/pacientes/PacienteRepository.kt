package br.pucpr.authserver.pacientes

import org.springframework.data.jpa.repository.JpaRepository

interface PacienteRepository : JpaRepository<Paciente, Long> {

}