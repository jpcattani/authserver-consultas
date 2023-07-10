package br.pucpr.authserver.consulta

import br.pucpr.authserver.medico.Medico
import br.pucpr.authserver.medico.MedicoRepository
import br.pucpr.authserver.paciente.Paciente
import br.pucpr.authserver.paciente.PacienteRepository
import br.pucpr.authserver.security.Jwt
import br.pucpr.authserver.users.UsersRepository
import br.pucpr.authserver.users.requests.LoginRequest
import br.pucpr.authserver.users.responses.LoginResponse
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class ConsultaService(
    val userRepository: UsersRepository,
    val repository: ConsultaRepository,
    val medicoRepository: MedicoRepository,
    val pacienteRepository: PacienteRepository,
    val jwt: Jwt
) {

    fun cadastrarMedico(nome: String, crm:String) {
        val medico = Medico(nome = nome, crm = crm)
        medicoRepository.save(medico)
    }

    fun cadastrarPaciente(nome: String, dataNascimento: LocalDate) {
        val paciente = Paciente(nome = nome, dataNascimento = dataNascimento)
        pacienteRepository.save(paciente)
    }

    fun marcarConsulta(dataConsulta: LocalDateTime, medicoId: Long, pacienteId: Long) {
        val medico = medicoRepository.findById(medicoId).orElseThrow { NoSuchElementException("Médico não encontrado") }
        val paciente = pacienteRepository.findById(pacienteId).orElseThrow { NoSuchElementException("Paciente não encontrado") }

        val consulta = Consulta(dataConsulta = dataConsulta, medico = medico, paciente = paciente)
        repository.save(consulta)
    }

    fun getById(id: Long):Consulta? = repository.findByIdOrNull(id)

    fun findAll(): List<Consulta> = repository.findAll()

    fun login(credentials: LoginRequest): LoginResponse? {
        val user = userRepository.findByEmail(credentials.email!!) ?: return null
        if (user.password != credentials.password) return null
        log.info("User logged in. id={} name={}", user.id, user.name)
        return LoginResponse(
            token = jwt.createToken(user),
            user.toResponse()
        )
    }

    fun delete(id: Long): Boolean {
        val consulta = repository.findByIdOrNull(id) ?: return false
        log.warn("Consulta id={} cancelada", consulta.id)
        repository.delete(consulta)
        return true
    }

    companion object {
        val log = LoggerFactory.getLogger(ConsultaService::class.java)
    }
}