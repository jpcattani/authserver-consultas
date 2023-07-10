package br.pucpr.authserver.medico.response

data class MedicoResponse (
    val id:Long,
    val nome: String?,
    val crm: String?
)