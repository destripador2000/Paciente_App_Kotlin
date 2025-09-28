package com.example.nexoapp // O tu paquete correspondiente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexoapp.ui.theme.NexoAppTheme // Asegúrate que tu tema esté aquí

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisTurnosScreen(
    // En el futuro, podrías recibir datos del turno desde un ViewModel
    // viewModel: TurnosViewModel = hiltViewModel()
    // Por ahora, usamos datos de ejemplo
    numeroActual: String = "42",
    tuPosicion: String = "5",
    tiempoEstimado: String = "18 minutos"
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Turnos", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant, // O el color que prefieras
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Padding para la TopAppBar de esta pantalla
                .verticalScroll(rememberScrollState()) // Para que sea desplazable si el contenido crece
                .padding(16.dp), // Padding general para el contenido de la columna
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp) // Espacio entre las tarjetas
        ) {
            TurnoActualCard(
                numeroActual = numeroActual,
                tuPosicion = tuPosicion,
                tiempoEstimado = tiempoEstimado
            )
            ConsejosTurnoCard()
        }
    }
}

@Composable
fun TurnoActualCard(numeroActual: String, tuPosicion: String, tiempoEstimado: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp), // Bordes redondeados
        colors = CardDefaults.cardColors(containerColor = Color.White), // Fondo blanco
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp) // Mayor padding interno para esta tarjeta
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Número actual",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant // Un gris no tan oscuro
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = numeroActual,
                fontSize = 64.sp, // Número grande
                fontWeight = FontWeight.Bold,
                color = Color(0xFF007AFF) // Color azul (puedes ajustarlo o usar MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Tu posición: $tuPosicion",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tiempo estimado: $tiempoEstimado",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun ConsejosTurnoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp), // Bordes redondeados
        colors = CardDefaults.cardColors(containerColor = Color.White), // Fondo blanco
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "💡 Consejos", // Puedes usar Text("💡") + Text(" Consejos") si el ícono no se renderiza bien
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Mantente atento a las notificaciones del hospital para cuando se acerque tu turno.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray, // Texto gris claro
                textAlign = TextAlign.Center,
                lineHeight = 20.sp // Para mejor legibilidad
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0F0F0) // Fondo gris para el preview
@Composable
fun MisTurnosScreenPreview() {
    NexoAppTheme { // Asegúrate de envolver con tu tema para que los estilos se apliquen
        MisTurnosScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun TurnoActualCardPreview() {
    NexoAppTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            TurnoActualCard(numeroActual = "42", tuPosicion = "5", tiempoEstimado = "18 minutos")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConsejosTurnoCardPreview() {
    NexoAppTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            ConsejosTurnoCard()
        }
    }
}


