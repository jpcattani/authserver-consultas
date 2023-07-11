package br.pucpr.authserver.medicos.response

data class MedicoResponse (
    val id:Long,
    val nome: String?,
    val crm: String?
)