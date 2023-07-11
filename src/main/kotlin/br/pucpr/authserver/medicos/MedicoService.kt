package br.pucpr.authserver.medicos

import br.pucpr.authserver.medicos.request.MedicoRequest
import br.pucpr.authserver.security.Jwt
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MedicoService (
    val repository: MedicoRepository,
    val jwt: Jwt
) {
    fun save(req: MedicoRequest): Medico {
        val medico = Medico(
            nome = req.nome!!,
            crm = req.crm!!
        )
        return repository.save(medico)
    }

    fun getById(id: Long): Medico? = repository.findByIdOrNull(id)

    fun findAll(): List<Medico> = repository.findAll()

    fun delete(id: Long): Boolean {
        val medico:Medico = repository.findByIdOrNull(id) ?: return false
        log.warn("Medico id={}, nome={} deletado", medico.id, medico.nome)
        repository.delete(medico)
        return true
    }

    companion object {
        val log = LoggerFactory.getLogger(MedicoService::class.java)
    }
}