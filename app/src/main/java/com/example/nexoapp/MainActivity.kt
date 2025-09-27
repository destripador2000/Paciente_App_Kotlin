package com.example.nexoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexoapp.ui.theme.NexoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NexoAppTheme {
                // Surface es un contenedor que nos permite establecer un color de fondo.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WelcomeScreen()
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el espacio disponible.
            .padding(32.dp), // Añade un padding alrededor del contenido.
        horizontalAlignment = Alignment.CenterHorizontally, // Centra horizontalmente todos los hijos.
        verticalArrangement = Arrangement.Center // Centra verticalmente los elementos con espacio entre ellos.
    ) {

        // Espaciador para empujar el contenido hacia el centro, creando margen superior.
        Spacer(modifier = Modifier.weight(1f))

        // Contenedor para la ilustración.
        Box(modifier = Modifier.weight(2f)) {
            Image(
                painter = painterResource(id = R.drawable.hospital_illustration), // Carga la imagen desde res/drawable.
                contentDescription = "Ilustración de un hospital", // Texto para accesibilidad.
                contentScale = ContentScale.Fit, // Ajusta la imagen para que sea visible completamente.
                modifier = Modifier.fillMaxSize() // La imagen ocupa el espacio del Box.
            )
        }

        // Título de bienvenida.
        Text(
            text = "Bienvenido a Paciente App",
            fontSize = 24.sp, // Tamaño de la fuente.
            fontWeight = FontWeight.Bold, // Fuente en negrita.
            color = MaterialTheme.colorScheme.onBackground // Color de texto principal.
        )

        // Espacio vertical entre el título y el subtítulo.
        Spacer(modifier = Modifier.height(16.dp))

        // Subtítulo descriptivo.
        Text(
            text = "Gestiona tus citas, resultados y turnos del hospital desde tu teléfono.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center, // Texto centrado.
            color = Color.Gray // Color de texto secundario.
        )

        // Espaciador para empujar los botones hacia abajo.
        Spacer(modifier = Modifier.weight(1f))

        // Botón principal de "Iniciar sesión".
        Button(
            onClick = { /* Acción del botón (no funcional por ahora) */ },
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el ancho.
                .height(50.dp), // Altura fija.
            shape = RoundedCornerShape(8.dp), // Bordes redondeados.
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF007AFF) // Color de fondo azul personalizado.
            )
        ) {
            Text(
                text = "Iniciar sesión",
                fontSize = 18.sp,
                color = Color.White // Texto del botón en color blanco.
            )
        }

        // Espacio vertical entre el botón y el texto de registro.
        Spacer(modifier = Modifier.height(24.dp))

        // Texto para la opción de registro.
        TextButton(
            onClick = { /* Acción para registrarse */ }
        ) {
            Text(
                text = "¿No tienes cuenta? Regístrate",
                color = Color.Gray, // Color de texto secundario.
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeScreenPreview() {
    NexoAppTheme {
        WelcomeScreen()
    }
}
