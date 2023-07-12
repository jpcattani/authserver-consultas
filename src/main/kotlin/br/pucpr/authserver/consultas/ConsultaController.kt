package br.pucpr.authserver.consultas

import br.pucpr.authserver.consultas.requests.ConsultaRequest
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalDateTime

@RestController
@RequestMapping("/consultas")
class ConsultaController(val service: ConsultaService) {

    @GetMapping("/consultas")
    fun getConsultas(
        @RequestParam(required = false) dataConsulta: LocalDate?,
        @RequestParam(defaultValue = "dataConsulta") orderBy: String,
        @RequestParam(defaultValue = "asc") direction: String
    ): List<Consulta> {

        //se dataConsulta diferente de null chama o metodo que filtra por data
        var consultas = if (dataConsulta != null) {
            service.findAllByDataConsulta(dataConsulta)
        } else {//senao lista todas as consultas
            service.findAll()
        }

        if(consultas.isNullOrEmpty()){
            log.warn("Lista de consultas vazia")
            return  emptyList()
        }

        // tipo de ordenação por data, nome do medico, ou nome do paciente
        val sortedConsultas = when (orderBy) {
            "dataConsulta" -> consultas?.sortedBy { it.dataConsulta }
            "medico" -> consultas?.sortedBy { it.medico.nome }
            "paciente" -> consultas?.sortedBy { it.paciente.nome }
            else -> consultas
        }

        // caso a direção for desc interte a ordem
        val sortedAndDirectionalConsultas = if (direction == "desc") {
            sortedConsultas?.reversed()
        } else {
            sortedConsultas
        }

        return sortedAndDirectionalConsultas!!
    }

    @GetMapping("/all")
    fun listConsultas() =
        service.findAll()
            .map { it.toResponse() }

    @Transactional
    @PostMapping
    fun createConsulta(@Valid @RequestBody req: ConsultaRequest) =

        service.save(req)
            .toResponse()
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping("/data/{dataConsulta}")
    fun listConsultasByDataConsulta(@Valid @PathVariable("dataConsulta") dataConsulta: LocalDate) =
        service.findAllByDataConsulta(dataConsulta)?.map { it.toResponse() }

     @GetMapping("/{id}")
    fun getConsulta(@PathVariable("id") id: Long) =
        service.getById(id)
            ?.let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "AuthServer")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Void> =
        if (service.delete(id)) ResponseEntity.ok().build()
        else ResponseEntity.notFound().build()

    companion object {
        val log = LoggerFactory.getLogger(ConsultaController::class.java)
    }
}