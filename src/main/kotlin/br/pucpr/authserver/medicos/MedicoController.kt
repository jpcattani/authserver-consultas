package br.pucpr.authserver.medicos

import br.pucpr.authserver.medicos.request.MedicoRequest
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/medicos")
class MedicoController(val service: MedicoService) {

    @GetMapping
    fun listMedicos() =
        service.findAll()
            .map { it.toResponse() }

    @Transactional
    @PostMapping
    fun createMedico(@Valid @RequestBody req: MedicoRequest) =
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
        else ResponseEntity.notFound().build()
}