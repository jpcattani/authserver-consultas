package br.pucpr.authserver.consulta

import br.pucpr.authserver.consulta.requests.ConsultaRequest
import br.pucpr.authserver.medico.request.MedicoRequest
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/consulta")
class ConsultaController(val service: ConsultaService) {
    @GetMapping
    fun listConsultas() =
        service.findAll()
            .map { it.toResponse() }

    @Transactional
    @PostMapping
    fun createMedico(@Valid @RequestBody req: ConsultaRequest) =
        service.save(req)
            .toResponse()
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping("/{id}")
    fun getMedico(@PathVariable("id") id: Long) =
        service.getById(id)
            ?.let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Void> =
        if (service.delete(id)) ResponseEntity.ok().build()
        else ResponseEntity.notFound().build()}