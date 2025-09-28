package com.example.nexoapp // Asegúrate que este sea tu paquete principal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// No necesitamos importar CitaMedica o EstadoCita con un subpaquete,
// ya que estarán en el mismo paquete com.example.nexoapp
// import com.example.nexoapp.CitaMedica // Ya no es necesario si está en el mismo paquete
// import com.example.nexoapp.EstadoCita // Ya no es necesario si está en el mismo paquete
import com.example.nexoapp.ui.theme.NexoAppTheme // Asumiendo que tu tema está aquí

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisCitasScreen(
    citas: List<CitaMedica> = sampleCitasData()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis citas", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    ) { paddingValues ->
        if (citas.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("No tienes citas programadas.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
            ) {
                items(citas, key = { it.id }) { cita ->
                    CitaMedicaCard(cita = cita, onAddReminderClicked = {
                        println("Añadir recordatorio para cita: ${cita.id}")
                    })
                }
            }
        }
    }
}

@Composable
fun CitaMedicaCard(cita: CitaMedica, onAddReminderClicked: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "${cita.doctorNombre} - ${cita.especialidad}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            InfoRow(label = "Fecha:", value = cita.fecha)
            InfoRow(label = "Hora:", value = cita.hora)
            InfoRow(label = "Ubicación:", value = cita.ubicacion)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = cita.estado.displayName,
                color = cita.estado.color,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onAddReminderClicked,
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(Icons.Filled.AddAlert, contentDescription = "Añadir recordatorio", modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Añadir recordatorio")
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.width(90.dp)
        )
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
    Spacer(modifier = Modifier.height(4.dp))
}

fun sampleCitasData(): List<CitaMedica> {
    return listOf(
        CitaMedica(
            id = "1",
            doctorNombre = "Dra. Ana Gómez",
            especialidad = "Pediatría",
            fecha = "2 de octubre de 2025",
            hora = "9:00 AM",
            ubicacion = "Sala A-201",
            estado = EstadoCita.CONFIRMADA
        ),
        CitaMedica(
            id = "2",
            doctorNombre = "Dr. Carlos Ruiz",
            especialidad = "Cardiología",
            fecha = "15 de noviembre de 2025",
            hora = "11:30 AM",
            ubicacion = "Consultorio 305",
            estado = EstadoCita.PENDIENTE
        ),
        CitaMedica(
            id = "3",
            doctorNombre = "Dra. Laura Méndez",
            especialidad = "Dermatología",
            fecha = "28 de noviembre de 2025",
            hora = "03:00 PM",
            ubicacion = "Clínica Piel Sana",
            estado = EstadoCita.CONFIRMADA
        )
    )
}

@Preview(showBackground = true, name = "Mis Citas con Datos")
@Composable
fun MisCitasScreenPreview() {
    NexoAppTheme {
        MisCitasScreen(citas = sampleCitasData())
    }
}

@Preview(showBackground = true, name = "Mis Citas Vacías")
@Composable
fun MisCitasScreenEmptyPreview() {
    NexoAppTheme {
        MisCitasScreen(citas = emptyList())
    }
}


