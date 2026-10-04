package com.example.myapplication

object Rotas {

    const val LOGIN = "login"
    const val CADASTRO = "cadastro"
    const val PRINCIPAL = "principal"
    const val NOTIFICACOES = "notificacoes"

    const val DETALHE_ESTABELECIMENTO = "detalhe_estabelecimento/{id}"
    const val DETALHE_AGENDAMENTO = "detalhe_agendamento/{id}"

    const val ABA_HOME = "aba_home"
    const val ABA_ESTABELECIMENTOS = "aba_estabelecimentos"
    const val ABA_AGENDAMENTOS = "aba_agendamentos"
    const val ABA_PERFIL = "aba_perfil"

    fun detalheEstabelecimento(id: Int) = "detalhe_estabelecimento/$id"

    fun detalheAgendamento(id: Int) = "detalhe_agendamento/$id"

}
