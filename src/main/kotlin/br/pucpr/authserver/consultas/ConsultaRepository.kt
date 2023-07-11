package br.pucpr.authserver.consultas

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface ConsultaRepository: JpaRepository<Consulta, Long> {
    fun findConsultaByDataConsulta(dataConsulta:LocalDateTime):List<Consulta>?

}