package br.pucpr.authserver.consulta

import br.pucpr.authserver.consulta.requests.ConsultaRequest
import br.pucpr.authserver.medico.Medico
import br.pucpr.authserver.medico.MedicoRepository
import br.pucpr.authserver.paciente.Paciente
import br.pucpr.authserver.paciente.PacienteRepository
import br.pucpr.authserver.security.Jwt
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ConsultaService(
    val repository: ConsultaRepository,
    val medicoRepository: MedicoRepository,
    val pacienteRepository: PacienteRepository,
    val jwt: Jwt
) {

    fun save(req: ConsultaRequest): Consulta {
        val medico:Medico = medicoRepository.findById(req.idMedico).orElseThrow { NoSuchElementException("Médico não encontrado") }
        val paciente:Paciente = pacienteRepository.findById(req.idPaciente).orElseThrow { NoSuchElementException("Paciente não encontrado") }

        val consulta = Consulta(
            paciente = paciente,
            medico = medico,
            dataConsulta = req.dataConsulta
        )
        return repository.save(consulta)
    }

    fun getById(id: Long): Consulta? = repository.findByIdOrNull(id)

    fun findAllByDataConsulta(dataConsulta: LocalDateTime): List<Consulta>? = repository.findConsultaByDataConsulta(dataConsulta)

    fun findAll(): List<Consulta> = repository.findAll()

    fun delete(id: Long): Boolean {
        val consulta:Consulta = repository.findByIdOrNull(id) ?: return false
        log.warn("Consulta id={}, data={} deletada", consulta.id, consulta.dataConsulta.toString())
        repository.delete(consulta)
        return true
    }

    companion object {
        val log = LoggerFactory.getLogger(ConsultaService::class.java)
    }
}