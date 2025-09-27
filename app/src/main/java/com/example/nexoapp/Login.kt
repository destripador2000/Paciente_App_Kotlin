package com.example.nexoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nexoapp.ui.theme.NexoAppTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NexoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // El NavController gestionará nuestras pantallas.
                    AppNavigation()
                }
            }
        }
    }
}

// 1. Configuración de la Navegación
@Composable
fun AppNavigation() {
    val navController = rememberNavController() // Crea y recuerda el controlador de navegación.

    NavHost(
        navController = navController,
        startDestination = "welcome" // La primera pantalla que se mostrará.
    ) {
        // Define la pantalla de bienvenida
        composable("welcome") {
            WelcomeScreen(navController = navController) // Pasamos el NavController para que pueda navegar.
        }
        // Define la pantalla de inicio de sesión
        composable("login") {
            LoginScreen()
        }
    }
}


// 2. Pantalla de Bienvenida (WelcomeScreen) actualizada
@Composable
fun WelcomeScreen(navController: NavController) { // Recibe el NavController como parámetro.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(modifier = Modifier.weight(2f)) {
            Image(
                painter = painterResource(id = R.drawable.hospital_illustration),
                contentDescription = "Ilustración de un hospital",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = "Bienvenido a Paciente App",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Gestiona tus citas, resultados y turnos del hospital desde tu teléfono.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            // Al hacer clic, navega a la pantalla "login".
            onClick = { navController.navigate("login") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF007AFF)
            )
        ) {
            Text(
                text = "Iniciar sesión",
                fontSize = 18.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        TextButton(
            onClick = { /* Acción para registrarse */ }
        ) {
            Text(
                text = "¿No tienes cuenta? Regístrate",
                color = Color.Gray,
                fontSize = 16.sp
            )
        }
    }
}

// 3. Nueva Pantalla de Inicio de Sesión (LoginScreen)
@Composable
fun LoginScreen() {
    // Estados para almacenar el texto del correo/cédula y la contraseña.
    var emailOrId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        // Título de la pantalla.
        Text(
            text = "Iniciar sesión",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Subtítulo descriptivo.
        Text(
            text = "Accede con tu correo electrónico o cédula",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campo de texto para Correo o Cédula.
        OutlinedTextField(
            value = emailOrId, // El valor actual del campo.
            onValueChange = { emailOrId = it }, // Actualiza el estado cuando el usuario escribe.
            label = { Text("Correo o Cédula") }, // Etiqueta que se muestra dentro o arriba del campo.
            modifier = Modifier.fillMaxWidth(), //ocupa all el ancho.
            singleLine = true // El campo no permitirá saltos de línea.
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para Contraseña.
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(), // Oculta el texto con ••••
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password) // Muestra el teclado de contraseña.
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Texto para "Olvidaste tu contraseña?".
        TextButton(
            onClick = { /* Acción para recuperar contraseña */ },
            modifier = Modifier.align(Alignment.Start), // Alinea el botón a la izquierda.
            contentPadding = PaddingValues(0.dp) // Quita el padding extra del TextButton.
        ) {
            Text(
                text = "¿Olvidaste tu contraseña?",
                color = Color(0xFF007AFF) // Color azul.
            )
        }

        // Espaciador para empujar el botón de Iniciar Sesión hacia abajo.
        Spacer(modifier = Modifier.weight(1f))

        // Botón principal de "Iniciar sesión".
        Button(
            onClick = { /* Acción del botón (no funcional por ahora) */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF007AFF)
            )
        ) {
            Text(
                text = "Iniciar sesión",
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}


// --- PREVISUALIZACIONES ---

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun WelcomeLoginScreenPreview() {
    NexoAppTheme {
        // Para la preview, creamos un NavController de prueba.
        val navController = rememberNavController()
        WelcomeScreen(navController = navController)
    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun LoginScreenPreview() {
    NexoAppTheme {
        LoginScreen()
    }
}


