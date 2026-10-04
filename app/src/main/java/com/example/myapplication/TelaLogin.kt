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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun TelaLogin(navController: NavHostController, viewModel: MeuViewModel) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier.size(70.dp).background(Color(0xFF009739), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.DateRange,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                "Bora Agendar?",
                color = Color(0xFF009739),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp
            )
            Text(
                "ENTRE NA SUA CONTA",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6B7A74)
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Column(modifier = Modifier.width(325.dp)) {

                CampoTexto(
                    titulo = "USUÁRIO",
                    valor = viewModel.user,
                    placeholder = "exemplo@email.com",
                    aoAlterar = { viewModel.alterarUser(it) }
                )

                Spacer(modifier = Modifier.padding(7.dp))

                CampoTexto(
                    titulo = "SENHA",
                    valor = viewModel.password,
                    placeholder = "Digite sua senha",
                    aoAlterar = { viewModel.alterarPassword(it) },
                    ehSenha = true
                )

                if (viewModel.erroLogin.isNotBlank()) {
                    Spacer(modifier = Modifier.padding(5.dp))
                    CaixaErro(viewModel.erroLogin)
                }

                Spacer(modifier = Modifier.padding(12.dp))

                Button(
                    onClick = {
                        if (viewModel.validarLogin()) {
                            navController.navigate(Rotas.PRINCIPAL) {
                                popUpTo(Rotas.LOGIN) { inclusive = true }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009739)),
                    modifier = Modifier.fillMaxWidth().height(55.dp),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("Entrar", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp) }

            }

            Spacer(modifier = Modifier.padding(10.dp))

            Row {
                Text("Novo por aqui? ", fontSize = 14.sp, color = Color(0xFF6B7A74))
                Text(
                    text = "Cadastre-se já",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A0DAB),
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { navController.navigate(Rotas.CADASTRO) }
                )
            }

        }
    }

}
