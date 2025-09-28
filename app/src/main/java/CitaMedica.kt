package com.example.nexoapp // Asegúrate que este sea tu paquete principal

import androidx.compose.ui.graphics.Color

// Data class para representar una Cita Médica
data class CitaMedica(
    val id: String, // Identificador único para la cita
    val doctorNombre: String,
    val especialidad: String,
    val fecha: String,
    val hora: String,
    val ubicacion: String,
    val estado: EstadoCita,
    val notasAdicionales: String? = null // Opcional
)

// Enum para el estado de la cita
enum class EstadoCita(val displayName: String, val color: Color) {
    CONFIRMADA("CONFIRMADA", Color(0xFF4CAF50)), // Verde
    PENDIENTE("PENDIENTE", Color.Gray),
    CANCELADA("CANCELADA", Color.Red)
}