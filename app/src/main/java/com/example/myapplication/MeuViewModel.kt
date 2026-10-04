package com.example.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MeuViewModel : ViewModel() {

    var user by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var nome by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var senha by mutableStateOf("")
        private set

    var cpf by mutableStateOf("")
        private set

    var endereco by mutableStateOf("")
        private set

    var dataNasc by mutableStateOf("")
        private set

    var cadastroFeito by mutableStateOf(false)
        private set

    var erroLogin by mutableStateOf("")
        private set

    var erroCadastro by mutableStateOf("")
        private set

    var mensagemPerfil by mutableStateOf("")
        private set

    var erroEstabelecimento by mutableStateOf("")
        private set

    var erroAgendamento by mutableStateOf("")
        private set

    private var proximoIdEstabelecimento = 4
    private var proximoIdAgendamento = 5
    private var proximoIdNotificacao = 5

    val estabelecimentos = mutableStateListOf(
        Estabelecimento(1, "Barbearia A", "Barbearia", "4.9", "Rua XV de Novembro, 120"),
        Estabelecimento(2, "Studio Bella", "Salão de beleza", "4.7", "Av. Sete de Setembro, 455"),
        Estabelecimento(3, "AutoShine", "Estética automotiva", "4.8", "Rua Brigadeiro Franco, 980")
    )

    val agendamentos = mutableStateListOf(
        Agendamento(1, 1, "Corte masculino", "25/10/2026", "14:30", 45.0, "Máquina 2 nas laterais", true),
        Agendamento(2, 1, "Corte e barba", "31/10/2026", "10:00", 70.0, "", false),
        Agendamento(3, 2, "Escova e hidratação", "01/11/2026", "16:00", 120.0, "Cabelo bem comprido", false),
        Agendamento(4, 3, "Lavagem completa", "05/11/2026", "09:30", 90.0, "Carro prata, placa ABC-1234", true)
    )

    val notificacoes = mutableStateListOf(
        Notificacao(1, "Agendamento confirmado", "A Barbearia A confirmou seu corte de 31/10 às 10:00.", "Hoje, 09:12", false),
        Notificacao(2, "Promoção no Studio Bella", "Hidratação com 20% de desconto até o fim do mês.", "Ontem, 18:40", false),
        Notificacao(3, "Avalie seu atendimento", "Como foi a lavagem na AutoShine? Deixe sua nota.", "2 dias atrás", true),
        Notificacao(4, "Bem-vindo ao Bora Agendar", "Cadastre seus estabelecimentos favoritos e agende em poucos cliques.", "5 dias atrás", true)
    )

    fun alterarUser(valor: String) {
        user = valor
        erroLogin = ""
    }

    fun alterarPassword(valor: String) {
        password = valor
        erroLogin = ""
    }

    fun alterarNome(valor: String) {
        nome = valor
        erroCadastro = ""
        mensagemPerfil = ""
    }

    fun alterarEmail(valor: String) {
        email = valor
        erroCadastro = ""
        mensagemPerfil = ""
    }

    fun alterarSenha(valor: String) {
        senha = valor
        erroCadastro = ""
        mensagemPerfil = ""
    }

    fun alterarCpf(valor: String) {
        cpf = valor
        erroCadastro = ""
        mensagemPerfil = ""
    }

    fun alterarEndereco(valor: String) {
        endereco = valor
        erroCadastro = ""
        mensagemPerfil = ""
    }

    fun alterarDataNasc(valor: String) {
        dataNasc = valor
        erroCadastro = ""
        mensagemPerfil = ""
    }

    fun salvarCadastro(): Boolean {
        if (nome.isBlank() || email.isBlank() || senha.isBlank() || cpf.isBlank() || endereco.isBlank() || dataNasc.isBlank()) {
            erroCadastro = "Preencha todos os campos para continuar"
            return false
        }
        if (senha.length < 4) {
            erroCadastro = "A senha precisa ter pelo menos 4 caracteres"
            return false
        }
        cadastroFeito = true
        erroCadastro = ""
        user = email
        password = ""
        return true
    }

    fun validarLogin(): Boolean {
        if (!cadastroFeito) {
            erroLogin = "Nenhuma conta cadastrada ainda. Cadastre-se primeiro."
            return false
        }
        if (user.isBlank() || password.isBlank()) {
            erroLogin = "Informe usuário e senha"
            return false
        }
        if (user != email || password != senha) {
            erroLogin = "Usuário ou senha incorretos"
            return false
        }
        erroLogin = ""
        return true
    }

    fun salvarPerfil() {
        if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
            mensagemPerfil = "Nome, email e senha não podem ficar vazios"
            return
        }
        mensagemPerfil = "Dados atualizados com sucesso"
    }

    fun sair() {
        user = ""
        password = ""
        erroLogin = ""
        mensagemPerfil = ""
    }

    fun adicionarEstabelecimento(nome: String, categoria: String, nota: String, endereco: String): Boolean {
        if (nome.isBlank() || categoria.isBlank()) {
            erroEstabelecimento = "Informe pelo menos nome e categoria"
            return false
        }
        estabelecimentos.add(
            Estabelecimento(
                id = proximoIdEstabelecimento,
                nome = nome,
                categoria = categoria,
                nota = if (nota.isBlank()) "0.0" else nota,
                endereco = if (endereco.isBlank()) "Endereço não informado" else endereco
            )
        )
        proximoIdEstabelecimento++
        erroEstabelecimento = ""
        return true
    }

    fun removerEstabelecimento(item: Estabelecimento) {
        estabelecimentos.remove(item)
        agendamentos.removeAll { it.idEstabelecimento == item.id }
    }

    fun buscarEstabelecimento(id: Int): Estabelecimento? {
        return estabelecimentos.find { it.id == id }
    }

    fun agendamentosDoEstabelecimento(id: Int): List<Agendamento> {
        return agendamentos.filter { it.idEstabelecimento == id }
    }

    fun totalGastoNoEstabelecimento(id: Int): Double {
        return agendamentos.filter { it.idEstabelecimento == id }.sumOf { it.preco }
    }

    fun adicionarAgendamento(idEstabelecimento: Int, servico: String, data: String, hora: String, preco: String): Boolean {
        if (idEstabelecimento == 0) {
            erroAgendamento = "Escolha um estabelecimento"
            return false
        }
        if (servico.isBlank() || data.isBlank() || hora.isBlank()) {
            erroAgendamento = "Preencha serviço, data e hora"
            return false
        }
        val valor = preco.replace(",", ".").toDoubleOrNull()
        if (valor == null) {
            erroAgendamento = "Preço inválido"
            return false
        }
        agendamentos.add(
            Agendamento(
                id = proximoIdAgendamento,
                idEstabelecimento = idEstabelecimento,
                servico = servico,
                data = data,
                hora = hora,
                preco = valor
            )
        )
        proximoIdAgendamento++
        erroAgendamento = ""
        return true
    }

    fun removerAgendamento(item: Agendamento) {
        agendamentos.remove(item)
    }

    fun alternarConcluido(item: Agendamento) {
        val posicao = agendamentos.indexOfFirst { it.id == item.id }
        if (posicao >= 0) {
            agendamentos[posicao] = item.copy(concluido = !item.concluido)
        }
    }

    fun buscarAgendamento(id: Int): Agendamento? {
        return agendamentos.find { it.id == id }
    }

    fun atualizarAgendamento(id: Int, servico: String, data: String, hora: String, preco: String, observacao: String): Boolean {
        val posicao = agendamentos.indexOfFirst { it.id == id }
        if (posicao < 0) {
            return false
        }
        if (servico.isBlank() || data.isBlank() || hora.isBlank()) {
            erroAgendamento = "Preencha serviço, data e hora"
            return false
        }
        val valor = preco.replace(",", ".").toDoubleOrNull()
        if (valor == null) {
            erroAgendamento = "Preço inválido"
            return false
        }
        agendamentos[posicao] = agendamentos[posicao].copy(
            servico = servico,
            data = data,
            hora = hora,
            preco = valor,
            observacao = observacao
        )
        erroAgendamento = ""
        return true
    }

    fun limparErroAgendamento() {
        erroAgendamento = ""
    }

    fun limparErroEstabelecimento() {
        erroEstabelecimento = ""
    }

    fun marcarNotificacaoLida(item: Notificacao) {
        val posicao = notificacoes.indexOfFirst { it.id == item.id }
        if (posicao >= 0) {
            notificacoes[posicao] = item.copy(lida = true)
        }
    }

    fun removerNotificacao(item: Notificacao) {
        notificacoes.remove(item)
    }

    fun adicionarNotificacao(titulo: String, mensagem: String) {
        notificacoes.add(0, Notificacao(proximoIdNotificacao, titulo, mensagem, "Agora", false))
        proximoIdNotificacao++
    }

    fun notificacoesNaoLidas(): Int {
        return notificacoes.count { !it.lida }
    }

}
