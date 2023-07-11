package br.pucpr.authserver.consultas

import br.pucpr.authserver.consultas.requests.ConsultaRequest
import br.pucpr.authserver.medicos.Medico
import br.pucpr.authserver.medicos.MedicoRepository
import br.pucpr.authserver.pacientes.Paciente
import br.pucpr.authserver.pacientes.PacienteRepository
import br.pucpr.authserver.security.Jwt
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class ConsultaService(
    val repository: ConsultaRepository,
    val medicoRepository: MedicoRepository,
    val pacienteRepository: PacienteRepository,
    val jwt: Jwt
) {

    fun save(req: ConsultaRequest): Consulta {

        log.warn("Consulta SAVE ")
       log.warn("Consulta save idMedico={}",req.idMedico.toString())

        log.warn("Consulta save idPaciente={}", req.idPaciente.toString())

        log.warn("Consulta save data={}",req.dataConsulta.toString())

        val medico:Medico = medicoRepository.findById(1).orElseThrow { NoSuchElementException("Médico não encontrado") }
        val paciente:Paciente = pacienteRepository.findById(1).orElseThrow { NoSuchElementException("Paciente não encontrado") }

        val consulta = Consulta(
            paciente = paciente,
            medico = medico,
            dataConsulta = req.dataConsulta
        )
        return repository.save(consulta)

    }

    fun getById(id: Long): Consulta? = repository.findByIdOrNull(id)

    fun findAllByDataConsulta(dataConsulta: LocalDate): List<Consulta>? = repository.findConsultaByDataConsulta(dataConsulta)

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