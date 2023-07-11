package br.pucpr.authserver.pacientes


import br.pucpr.authserver.pacientes.request.PacienteRequest
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PacienteService (
    val repository: PacienteRepository
) {
    fun save(req: PacienteRequest): Paciente {
        val paciente = Paciente(
            nome = req.nome!!,
            telefone = req.telefone!!
        )
        return repository.save(paciente)
    }

    fun getById(id: Long): Paciente? = repository.findByIdOrNull(id)

    fun findAll(): List<Paciente> = repository.findAll()

    fun delete(id: Long): Boolean {
        val paciente: Paciente = repository.findByIdOrNull(id) ?: return false
        log.warn("Paciente id={}, nome={} deletado", paciente.id, paciente.nome)
        repository.delete(paciente)
        return true
    }

    companion object {
        val log = LoggerFactory.getLogger(PacienteService::class.java)
    }
}