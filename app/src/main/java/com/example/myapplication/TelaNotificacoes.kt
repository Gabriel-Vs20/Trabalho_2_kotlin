package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaNotificacoes(navController: NavHostController, viewModel: MeuViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Notificações",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF009739))
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF4F7F6))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                item {
                    Text(
                        "${viewModel.notificacoesNaoLidas()} não lidas",
                        fontSize = 12.sp,
                        color = Color(0xFF6B7A74)
                    )
                }

                items(viewModel.notificacoes, key = { it.id }) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.marcarNotificacaoLida(item) },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (item.lida) Color.White else Color(0xFFF2FBF5)
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(14.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(
                                        if (item.lida) Color(0xFFF4F7F6) else Color(0xFF009739),
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Notifications,
                                    contentDescription = null,
                                    tint = if (item.lida) Color(0xFF9AA5A1) else Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.padding(5.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        item.titulo,
                                        fontWeight = if (item.lida) FontWeight.Medium else FontWeight.ExtraBold,
                                        fontSize = 14.sp,
                                        color = Color(0xFF1F2937),
                                        modifier = Modifier.weight(1f)
                                    )
                                    if (!item.lida) {
                                        Box(
                                            modifier = Modifier.size(8.dp).background(Color(0xFF009739), CircleShape)
                                        )
                                    }
                                }
                                Text(item.mensagem, fontSize = 13.sp, color = Color(0xFF6B7A74))
                                Spacer(modifier = Modifier.padding(2.dp))
                                Text(item.quando, fontSize = 11.sp, color = Color(0xFF9AA5A1))
                            }

                            IconButton(onClick = { viewModel.removerNotificacao(item) }) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Remover",
                                    tint = Color(0xFFC62828)
                                )
                            }
                        }
                    }
                }

                if (viewModel.notificacoes.isEmpty()) {
                    item {
                        CaixaVazia("Nenhuma notificação por aqui")
                    }
                }

            }
        }
    }

}
