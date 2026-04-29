package com.workly.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.outlined.WorkOutline
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.workly.ui.theme.BackgroundLight
import com.workly.ui.theme.BluePrimary
import com.workly.ui.theme.GreenStatus
import com.workly.ui.theme.OrangeStatus

@Composable
fun HomeScreen(
    userType: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 84.dp)
        ) {
            TopHeader(userType = userType)

            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (userType == "Cliente") {
                    ClientContent()
                } else {
                    WorkerContent()
                }
            }
        }

        BottomMenu(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        )
    }
}

@Composable
private fun TopHeader(userType: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BluePrimary)
            .padding(20.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = if (userType == "Cliente") "Olá," else "Bem-vindo de volta,",
                color = Color.White.copy(alpha = 0.9f)
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = if (userType == "Cliente") "Maria Silva" else "Carlos Oliveira",
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(16.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        if (userType == "Cliente") "Buscar profissionais ou serviços..."
                        else "Buscar oportunidades..."
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )
        }
    }
}

@Composable
private fun ClientContent() {
    Card(
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(BluePrimary, RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Outlined.Add, contentDescription = null, tint = Color.White)
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text("Publicar Nova Demanda", fontWeight = FontWeight.SemiBold)
                Text("Encontre o profissional ideal", color = Color(0xFF4B5563))
            }

            Text("→", color = BluePrimary)
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        StatCard("2", "Ativas", Icons.Outlined.AccessTime, OrangeStatus, Modifier.weight(1f))
        StatCard("25", "Propostas", Icons.Outlined.ChatBubbleOutline, BluePrimary, Modifier.weight(1f))
        StatCard("8", "Finalizadas", Icons.Outlined.CheckCircleOutline, GreenStatus, Modifier.weight(1f))
    }

    SectionHeader("Minhas Demandas")

    DemandCard("Instalação de ar condicionado", "Climatização", "R$ 500 - R$ 800", "8 propostas", "Ativa")
    DemandCard("Pintura de sala e quartos", "Pintura", "R$ 1.200 - R$ 1.800", "12 propostas", "Ativa")
    DemandCard("Conserto de vazamento", "Hidráulica", "R$ 200 - R$ 400", "5 propostas", "Em andamento")
}

@Composable
private fun WorkerContent() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        StatCard("12", "Propostas", Icons.Outlined.WorkOutline, BluePrimary, Modifier.weight(1f))
        StatCard("4.8", "Avaliação", Icons.Outlined.StarOutline, Color(0xFFF4B400), Modifier.weight(1f))
        StatCard("R$ 3.2k", "Ganhos", Icons.Outlined.AttachMoney, GreenStatus, Modifier.weight(1f))
    }

    Text(
        text = "Ações Rápidas",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {},
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("Meu Perfil")
        }

        OutlinedButton(
            onClick = {},
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("Ver Demandas")
        }
    }

    SectionHeader("Minhas Propostas")

    ProposalCard("Maria Silva", "Instalação elétrica residencial", "R$ 800", "Pendente")
    ProposalCard("João Santos", "Manutenção de ar condicionado", "R$ 300", "Aceita")
    ProposalCard("Ana Costa", "Pintura de apartamento", "R$ 1.500", "Negociando")
}

@Composable
private fun StatCard(
    value: String,
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    tint: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(tint.copy(alpha = 0.12f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = tint)
            }

            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Text(text = label, color = Color(0xFF4B5563))
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(text = "Ver todas", color = BluePrimary)
    }
}

@Composable
private fun DemandCard(
    title: String,
    category: String,
    budget: String,
    proposals: String,
    status: String
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(title, fontWeight = FontWeight.SemiBold)
                AssistChip(onClick = {}, label = { Text(status) })
            }

            Text(category, color = Color(0xFF4B5563))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(budget, color = BluePrimary, fontWeight = FontWeight.SemiBold)
                Text(proposals, color = BluePrimary)
            }
        }
    }
}

@Composable
private fun ProposalCard(
    name: String,
    service: String,
    price: String,
    status: String
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(name, fontWeight = FontWeight.SemiBold)
                Text(service, color = Color(0xFF4B5563))
                Spacer(modifier = Modifier.size(6.dp))
                Text(price, color = BluePrimary, fontWeight = FontWeight.SemiBold)
            }

            AssistChip(onClick = {}, label = { Text(status) })
        }
    }
}

@Composable
private fun BottomMenu(modifier: Modifier = Modifier) {
    NavigationBar(modifier = modifier.fillMaxWidth()) {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Outlined.Home, contentDescription = null) },
            label = { Text("Início") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.Search, contentDescription = null) },
            label = { Text("Buscar") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.ChatBubbleOutline, contentDescription = null) },
            label = { Text("Mensagens") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.Person, contentDescription = null) },
            label = { Text("Perfil") }
        )
    }
}