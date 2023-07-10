package br.pucpr.authserver.paciente


import br.pucpr.authserver.paciente.request.PacienteRequest
import br.pucpr.authserver.security.Jwt
import br.pucpr.authserver.users.UsersRepository
import br.pucpr.authserver.users.UsersService
import br.pucpr.authserver.users.requests.LoginRequest
import br.pucpr.authserver.users.responses.LoginResponse
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
            dataNascimento = req.dataNascimento!!
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