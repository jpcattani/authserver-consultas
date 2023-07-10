package br.pucpr.authserver.consulta

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface ConsultaRepository: JpaRepository<Consulta, Long> {
    fun findConsultaByDataConsulta(dataConsulta:LocalDateTime):Consulta?

}