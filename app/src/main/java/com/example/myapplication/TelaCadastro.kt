package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
fun TelaCadastro(navController: NavHostController, viewModel: MeuViewModel) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Bora Agendar?",
                color = Color(0xFF009739),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp
            )
            Text(
                "CRIE SUA CONTA FACILMENTE",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6B7A74)
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Column(modifier = Modifier.width(325.dp)) {

                CampoTexto(
                    titulo = "NOME COMPLETO",
                    valor = viewModel.nome,
                    placeholder = "Digite seu nome",
                    aoAlterar = { viewModel.alterarNome(it) }
                )
                Spacer(modifier = Modifier.padding(6.dp))

                CampoTexto(
                    titulo = "EMAIL",
                    valor = viewModel.email,
                    placeholder = "exemplo@email.com",
                    aoAlterar = { viewModel.alterarEmail(it) }
                )
                Spacer(modifier = Modifier.padding(6.dp))

                CampoTexto(
                    titulo = "SENHA",
                    valor = viewModel.senha,
                    placeholder = "Mínimo 4 caracteres",
                    aoAlterar = { viewModel.alterarSenha(it) },
                    ehSenha = true
                )
                Spacer(modifier = Modifier.padding(6.dp))

                CampoTexto(
                    titulo = "CPF",
                    valor = viewModel.cpf,
                    placeholder = "000.000.000-00",
                    aoAlterar = { viewModel.alterarCpf(it) }
                )
                Spacer(modifier = Modifier.padding(6.dp))

                CampoTexto(
                    titulo = "ENDEREÇO",
                    valor = viewModel.endereco,
                    placeholder = "Rua, Número, Bairro",
                    aoAlterar = { viewModel.alterarEndereco(it) }
                )
                Spacer(modifier = Modifier.padding(6.dp))

                CampoTexto(
                    titulo = "DATA DE NASC.",
                    valor = viewModel.dataNasc,
                    placeholder = "DD/MM/YYYY",
                    aoAlterar = { viewModel.alterarDataNasc(it) }
                )

                if (viewModel.erroCadastro.isNotBlank()) {
                    Spacer(modifier = Modifier.padding(5.dp))
                    CaixaErro(viewModel.erroCadastro)
                }

                Spacer(modifier = Modifier.padding(12.dp))

                Button(
                    onClick = {
                        if (viewModel.salvarCadastro()) {
                            navController.navigate(Rotas.LOGIN) {
                                popUpTo(Rotas.LOGIN) { inclusive = true }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009739)),
                    modifier = Modifier.fillMaxWidth().height(55.dp),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("Salvar", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp) }

                Spacer(modifier = Modifier.padding(8.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text("Já tem conta? ", fontSize = 14.sp, color = Color(0xFF6B7A74))
                    Text(
                        text = "Entrar",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A0DAB),
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            navController.navigate(Rotas.LOGIN) {
                                popUpTo(Rotas.LOGIN) { inclusive = true }
                            }
                        }
                    )
                }

            }

        }
    }

}
