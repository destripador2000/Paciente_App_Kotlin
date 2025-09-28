package com.example.nexoapp // O tu paquete correspondiente

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings // Icono de engranaje
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexoapp.ui.theme.NexoAppTheme // Asegúrate que tu tema esté aquí
import java.util.UUID // Para generar IDs únicos

// --- Definiciones de Datos AQUÍ ---
data class NotificacionItem(
    val id: String = UUID.randomUUID().toString(), // Identificador único
    val titulo: String,
    val subtitulo: String,
    val tipo: TipoNotificacion = TipoNotificacion.INFO // Podrías añadir tipos para diferentes iconos o acciones
)

enum class TipoNotificacion {
    RESULTADO,
    RECORDATORIO,
    MENSAJE,
    INFO // General
}
// --- Fin de Definiciones de Datos ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificacionesScreen(
    // En el futuro, podrías recibir notificaciones desde un ViewModel
    // viewModel: NotificacionesViewModel = hiltViewModel()
    // Por ahora, usamos datos de ejemplo
    notificaciones: List<NotificacionItem> = sampleNotificacionesData()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notificaciones",
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif, // Fuente sans-serif
                            fontSize = 20.sp,
                            color = Color.Black // Color negro (#000000)
                        )
                    )
                },
                actions = {
                    IconButton(onClick = { /* TODO: Acción para ajustes de notificación */ }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Ajustes de notificaciones"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface, // Fondo para la TopAppBar
                    titleContentColor = Color.Black,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface // Color para el icono de acciones
                )
            )
        }
    ) { paddingValues ->
        if (notificaciones.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No tienes notificaciones.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF757575) // Gris claro
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Padding para la TopAppBar
                    .padding(horizontal = 16.dp), // Padding horizontal para la lista
                verticalArrangement = Arrangement.spacedBy(12.dp), // Espaciado vertical entre tarjetas
                contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp) // Espacio arriba y abajo de la lista
            ) {
                items(notificaciones, key = { it.id }) { notificacion ->
                    NotificacionCard(notificacion = notificacion)
                }
            }
        }
    }
}

@Composable
fun NotificacionCard(notificacion: NotificacionItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp), // Bordes redondeados: 12dp
        colors = CardDefaults.cardColors(containerColor = Color.White), // Fondo: blanco (#FFFFFF)
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp) // Sombra suave para profundidad
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp) // Padding interno: 16dp
                .fillMaxWidth()
        ) {
            Text(
                text = notificacion.titulo,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp)) // Pequeño espacio entre título y subtítulo
            Text(
                text = notificacion.subtitulo,
                color = Color(0xFF757575), // Subtítulo en gris claro (#757575)
                fontSize = 14.sp
            )
        }
    }
}

// Datos de ejemplo para previsualización y pruebas iniciales
fun sampleNotificacionesData(): List<NotificacionItem> {
    return listOf(
        NotificacionItem(
            titulo = "Resultado disponible",
            subtitulo = "Perfil lipídico - 02 May 2025",
            tipo = TipoNotificacion.RESULTADO
        ),
        NotificacionItem(
            titulo = "Recordatorio de cita",
            subtitulo = "Cita con Dra. Gómez el 02 Oct 2025",
            tipo = TipoNotificacion.RECORDATORIO
        ),
        NotificacionItem(
            titulo = "Mensaje importante",
            subtitulo = "Cambios en horario de atención",
            tipo = TipoNotificacion.MENSAJE
        ),
        NotificacionItem(
            titulo = "Actualización de la app",
            subtitulo = "Nuevas funcionalidades disponibles. ¡Actualiza ahora!",
            tipo = TipoNotificacion.INFO
        )
    )
}

@Preview(showBackground = true, name = "Notificaciones con Datos")
@Composable
fun NotificacionesScreenPreview() {
    NexoAppTheme { // Asegúrate de que tu tema envuelve el Preview
        NotificacionesScreen(notificaciones = sampleNotificacionesData())
    }
}

@Preview(showBackground = true, name = "Notificaciones Vacías")
@Composable
fun NotificacionesScreenEmptyPreview() {
    NexoAppTheme {
        NotificacionesScreen(notificaciones = emptyList())
    }
}

@Preview(showBackground = true)
@Composable
fun NotificacionCardPreview() {
    NexoAppTheme {
        Box(modifier = Modifier.padding(16.dp)) { // Añade un padding alrededor para mejor visualización
            NotificacionCard(
                notificacion = NotificacionItem(
                    titulo = "Título de prueba",
                    subtitulo = "Este es un subtítulo de prueba para la tarjeta de notificación."
                )
            )
        }
    }
}
