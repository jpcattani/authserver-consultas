package br.pucpr.authserver.consultas

import br.pucpr.authserver.users.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate
import java.time.LocalDateTime

interface ConsultaRepository: JpaRepository<Consulta, Long> {

    @Query(
        value = "select c from Consulta c" +
                " where cast(c.dataConsulta as localdate ) = :dataConsulta" +
                " order by c.dataConsulta asc"
    )
    fun findConsultaByDataConsulta(dataConsulta: LocalDate): List<Consulta>?

}