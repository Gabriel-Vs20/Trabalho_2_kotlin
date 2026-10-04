# Bora Agendar

Aplicativo Android de agendamento para barbearias, salões de beleza e estética automotiva. O usuário cadastra os estabelecimentos que costuma frequentar, marca horários, acompanha o que já foi feito e vê quanto gastou em cada lugar.

Trabalho 2 da disciplina de Desenvolvimento para Dispositivos Móveis.

Integrantes: Gabriel Valenga Silva, Rafael Moura Machado

---

## Como rodar

**O que é preciso ter instalado**

- Android Studio Ladybug ou mais recente
- JDK 11 (já vem embutido no Android Studio)
- Um emulador com API 24 ou superior, ou um celular Android com depuração USB ativada

**Passos**

1. Clone o repositório:

```
git clone https://github.com/Gabriel-Vs20/Trabalho_2_kotlin/
```

2. No Android Studio, use File > Open e aponte para a pasta raiz do projeto, aquela que contém o arquivo `settings.gradle.kts`. Não abra a pasta `app` direto, o Gradle não monta o módulo dessa forma.

3. Aguarde o Gradle Sync terminar. Na primeira vez ele baixa as dependências e pode demorar alguns minutos.

4. Selecione um emulador ou dispositivo na barra superior e clique em Run.

**Se o build falhar**

A causa mais comum é o Android Studio sugerir a atualização das dependências de navegação e lifecycle. As versões mais novas exigem AGP 9.1 e `compileSdk 37`, enquanto este projeto usa AGP 8.8.0 com `compileSdk 35`. Se aceitar a sugestão, o build quebra com erros de AAR metadata. As versões do arquivo `gradle/libs.versions.toml` estão travadas de propósito.

---

## Como testar o aplicativo

O login só aceita os dados cadastrados dentro do próprio aplicativo, e nada é gravado em disco, então na primeira execução é preciso passar pelo cadastro.

1. Na tela inicial, toque em "Cadastre-se já"
2. Preencha todos os campos. A senha precisa ter pelo menos quatro caracteres
3. Toque em Salvar, o que leva de volta ao login
4. Entre usando o email e a senha que acabou de cadastrar

Tentar entrar com dados diferentes mostra uma mensagem de erro e não avança, de propósito.

Depois de entrar, o aplicativo já vem com três estabelecimentos, quatro agendamentos e quatro notificações de exemplo, para que nenhuma tela apareça vazia.

---

## Funcionalidades

- Cadastro de conta com validação de campos obrigatórios e de tamanho mínimo da senha
- Login que confere usuário e senha contra o cadastro
- Lista de estabelecimentos, com adicionar e remover
- Lista de agendamentos, com adicionar, remover e marcar como concluído
- Tela de detalhe do estabelecimento, com total gasto, ticket médio e os agendamentos daquele local
- Tela de detalhe do agendamento, com edição no lugar, conclusão e exclusão
- Tela de notificações, com marcação de lida e exclusão, e contador no sino da barra superior
- Perfil com edição dos dados da conta e saída da sessão

---

## Telas

| Tela | Arquivo | O que faz |
|---|---|---|
| Login | `TelaLogin.kt` | Entrada no aplicativo, com validação |
| Cadastro | `TelaCadastro.kt` | Criação da conta, com rolagem vertical |
| Principal | `MinhaTela.kt` | Contêiner com barra superior, barra inferior e as abas |
| Início | `AbaHome.kt` | Painel com favoritos e horários |
| Locais | `AbaEstabelecimentos.kt` | Lista de estabelecimentos |
| Agenda | `AbaAgendamentos.kt` | Lista de agendamentos |
| Perfil | `AbaPerfil.kt` | Dados da conta e indicadores |
| Detalhe do estabelecimento | `TelaDetalheEstabelecimento.kt` | Dados, resumo calculado e agendamentos do local |
| Detalhe do agendamento | `TelaDetalheAgendamento.kt` | Edição, conclusão e exclusão |
| Notificações | `TelaNotificacoes.kt` | Avisos do aplicativo |

---

## Estrutura do código

Todos os arquivos ficam em `app/src/main/java/com/example/myapplication`.

```
MainActivity.kt                    ponto de entrada, chama AppNavigation
AppNavigation.kt                   NavHost central, cria o ViewModel
Rotas.kt                           constantes das rotas e funções com argumento
Modelos.kt                         data classes Estabelecimento, Agendamento e Notificacao
MeuViewModel.kt                    estado do aplicativo e as listas reativas
Componentes.kt                     composables reutilizados entre telas
MinhaTela.kt                       Scaffold e NavHost interno das abas
BottomBarNav.kt                    barra de navegação inferior
TelaLogin.kt
TelaCadastro.kt
AbaHome.kt
AbaEstabelecimentos.kt
AbaAgendamentos.kt
AbaPerfil.kt
TelaDetalheEstabelecimento.kt
TelaDetalheAgendamento.kt
TelaNotificacoes.kt
```

---

## Navegação

São dois NavHost. O externo controla as telas que ocupam a tela inteira, o interno controla as abas.

```
AppNavigation (NavHost externo)
├── login
├── cadastro
├── principal  ──────> MinhaTela (NavHost interno)
│                      ├── aba_home
│                      ├── aba_estabelecimentos
│                      ├── aba_agendamentos
│                      └── aba_perfil
├── notificacoes
├── detalhe_estabelecimento/{id}
└── detalhe_agendamento/{id}
```

As duas últimas recebem o id como `NavType.IntType` e buscam o item correspondente no ViewModel.

---

## Tecnologias

- Kotlin 2.0.0
- Jetpack Compose, BOM 2024.04.01
- Material 3
- Navigation Compose 2.8.9
- Lifecycle ViewModel Compose 2.8.7
- Android Gradle Plugin 8.8.0
- compileSdk 35, minSdk 24

---

## Limitação conhecida

Os dados vivem apenas na memória, em `mutableStateListOf` dentro do ViewModel. Fechar o aplicativo apaga a conta criada e os itens adicionados, voltando aos dados de exemplo. Isso está de acordo com a seção 5 do enunciado, que indica a persistência como assunto do próximo trabalho.
