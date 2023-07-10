package br.pucpr.authserver.paciente

import br.pucpr.authserver.paciente.request.PacienteRequest
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/paciente")
class PacienteController(val service: PacienteService) {

    @GetMapping
    fun listPacientes() =
        service.findAll()
            .map { it.toResponse() }

    @Transactional
    @PostMapping
    fun createPaciente(@Valid @RequestBody req: PacienteRequest) =
        service.save(req)
            .toResponse()
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping("/{id}")
    fun getPaciente(@PathVariable("id") id: Long) =
        service.getById(id)
            ?.let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Void> =
        if (service.delete(id)) ResponseEntity.ok().build()
        else ResponseEntity.notFound().build()
}
