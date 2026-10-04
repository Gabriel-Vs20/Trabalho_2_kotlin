package com.example.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBarNav(navInterno: NavHostController) {

    val backStackEntry by navInterno.currentBackStackEntryAsState()
    val rotaAtual = backStackEntry?.destination?.route

    val cores = NavigationBarItemDefaults.colors(
        selectedIconColor = Color.White,
        unselectedIconColor = Color(0xFFBFE3CC),
        selectedTextColor = Color.White,
        unselectedTextColor = Color(0xFFBFE3CC),
        indicatorColor = Color(0xFF007A2E)
    )

    NavigationBar(containerColor = Color(0xFF009739)) {

        NavigationBarItem(
            selected = rotaAtual == Rotas.ABA_HOME,
            onClick = { irPara(navInterno, Rotas.ABA_HOME) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Início") },
            label = { Text("Início", fontSize = 11.sp) },
            colors = cores
        )

        NavigationBarItem(
            selected = rotaAtual == Rotas.ABA_ESTABELECIMENTOS,
            onClick = { irPara(navInterno, Rotas.ABA_ESTABELECIMENTOS) },
            icon = { Icon(Icons.Default.Search, contentDescription = "Locais") },
            label = { Text("Locais", fontSize = 11.sp) },
            colors = cores
        )

        NavigationBarItem(
            selected = rotaAtual == Rotas.ABA_AGENDAMENTOS,
            onClick = { irPara(navInterno, Rotas.ABA_AGENDAMENTOS) },
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Agenda") },
            label = { Text("Agenda", fontSize = 11.sp) },
            colors = cores
        )

        NavigationBarItem(
            selected = rotaAtual == Rotas.ABA_PERFIL,
            onClick = { irPara(navInterno, Rotas.ABA_PERFIL) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil", fontSize = 11.sp) },
            colors = cores
        )

    }

}

private fun irPara(navInterno: NavHostController, rota: String) {
    navInterno.navigate(rota) {
        popUpTo(navInterno.graph.startDestinationId) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
