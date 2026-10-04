package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val viewModel: MeuViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Rotas.LOGIN
    ) {

        composable(Rotas.LOGIN) {
            TelaLogin(navController, viewModel)
        }

        composable(Rotas.CADASTRO) {
            TelaCadastro(navController, viewModel)
        }

        composable(Rotas.PRINCIPAL) {
            MinhaTela(navController, viewModel)
        }

        composable(Rotas.NOTIFICACOES) {
            TelaNotificacoes(navController, viewModel)
        }

        composable(
            route = Rotas.DETALHE_ESTABELECIMENTO,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            TelaDetalheEstabelecimento(navController, viewModel, id)
        }

        composable(
            route = Rotas.DETALHE_AGENDAMENTO,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            TelaDetalheAgendamento(navController, viewModel, id)
        }

    }

}
