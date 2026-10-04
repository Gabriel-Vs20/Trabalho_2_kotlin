package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinhaTela(navController: NavHostController, viewModel: MeuViewModel) {

    val navInterno = rememberNavController()
    val naoLidas = viewModel.notificacoesNaoLidas()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Bora Agendar?",
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 22.sp
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Rotas.NOTIFICACOES) }) {
                        Box {
                            Icon(
                                Icons.Default.Notifications,
                                contentDescription = "Notificações",
                                tint = Color.White
                            )
                            if (naoLidas > 0) {
                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .offset(x = 10.dp, y = (-4).dp)
                                        .background(Color(0xFFC62828), CircleShape)
                                        .align(Alignment.TopEnd),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        naoLidas.toString(),
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF009739)
                )
            )
        },
        bottomBar = { BottomBarNav(navInterno) }
    ) { innerPadding ->

        NavHost(
            navController = navInterno,
            startDestination = Rotas.ABA_HOME,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Rotas.ABA_HOME) { AbaHome(navController, viewModel) }
            composable(Rotas.ABA_ESTABELECIMENTOS) { AbaEstabelecimentos(navController, viewModel) }
            composable(Rotas.ABA_AGENDAMENTOS) { AbaAgendamentos(navController, viewModel) }
            composable(Rotas.ABA_PERFIL) { AbaPerfil(navController, viewModel) }

        }

    }

}
