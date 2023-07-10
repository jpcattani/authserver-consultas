package br.pucpr.authserver.paciente

import br.pucpr.authserver.consulta.ConsultaService
import br.pucpr.authserver.exception.BadRequestException
import br.pucpr.authserver.medico.Medico
import br.pucpr.authserver.paciente.request.PacienteRequest
import br.pucpr.authserver.security.Jwt
import br.pucpr.authserver.users.UsersRepository
import br.pucpr.authserver.users.UsersService
import br.pucpr.authserver.users.requests.LoginRequest
import br.pucpr.authserver.users.responses.LoginResponse
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull

class PacienteService (
    val repository: PacienteRepository,
    val repositoryUsers: UsersRepository,
    val jwt: Jwt
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

    fun login(credentials: LoginRequest): LoginResponse? {
        val user = repositoryUsers.findByEmail(credentials.email!!) ?: return null
        if (user.password != credentials.password) return null
        UsersService.log.info("User logged in. id={} name={}", user.id, user.name)
        return LoginResponse(
            token = jwt.createToken(user),
            user.toResponse()
        )
    }


    companion object {
        val log = LoggerFactory.getLogger(ConsultaService::class.java)
    }
}