package com.example.nexoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nexoapp.ui.theme.NexoAppTheme

class Principal : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NexoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PrincipalAppNavigation()
                }
            }
        }
    }
}

// 1. GESTOR DE NAVEGACIÓN (ACTUALIZADO)
@Composable
fun PrincipalAppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "welcome" // La app empieza en la pantalla de bienvenida
    ) {
        composable("welcome") {
            PrincipalWelcomeScreen(navController = navController)
        }
        composable("login") {
            // Pasamos el navController para que el botón de login nos lleve al home.
            LoginScreen(navController = navController)
        }
        // Nueva ruta para toda la sección principal de la app (con la BottomBar)
        composable("main") {
            MainScreen()
        }
    }
}

// --- PANTALLAS DE BIENVENIDA Y LOGIN (ACTUALIZADAS PARA NAVEGAR A "main") ---

@Composable
fun PrincipalWelcomeScreen(navController: NavController) {
    // ... (El código de WelcomeScreen no cambia, solo su navegación)
    // El onClick del botón "Iniciar Sesión" sigue siendo navController.navigate("login")
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
        Text(text = "Bienvenido a Paciente App", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Gestiona tus citas, resultados y turnos del hospital desde tu teléfono.", fontSize = 16.sp, textAlign = TextAlign.Center, color = Color.Gray)
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { navController.navigate("login") }, // Navega a Login
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF))
        ) {
            Text(text = "Iniciar sesión", fontSize = 18.sp, color = Color.White)
        }
        Spacer(modifier = Modifier.height(24.dp))
        TextButton(onClick = { /* Acción para registrarse */ }) {
            Text(text = "¿No tienes cuenta? Regístrate", color = Color.Gray, fontSize = 16.sp)
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) { // Recibe NavController
    var emailOrId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(32.dp)) {
        //... Campos de texto y otros elementos
        Text(text = "Iniciar sesión", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Accede con tu correo electrónico o cédula", fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(value = emailOrId, onValueChange = { emailOrId = it }, label = { Text("Correo o Cédula") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") }, modifier = Modifier.fillMaxWidth(), singleLine = true, visualTransformation = PasswordVisualTransformation(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { /*...*/ }, modifier = Modifier.align(Alignment.Start), contentPadding = PaddingValues(0.dp)) {
            Text(text = "¿Olvidaste tu contraseña?", color = Color(0xFF007AFF))
        }
        Spacer(modifier = Modifier.weight(1f))

        // ¡ACCIÓN CLAVE! Al iniciar sesión, navegamos a la pantalla principal "main".
        Button(
            onClick = {
                // Aquí iría tu lógica de validación de backend.
                // Si es exitoso, navega a la pantalla principal.
                navController.navigate("main") {
                    // Limpia el stack de navegación para que el usuario no pueda
                    // volver atrás a la pantalla de login con el botón de retroceso.
                    popUpTo("welcome") { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF))
        ) {
            Text(text = "Iniciar sesión", fontSize = 18.sp, color = Color.White)
        }
    }
}


// 2. ESTRUCTURA DE LA PANTALLA PRINCIPAL (CON BOTTOM BAR)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") { HomeScreen() }
            // ---- ACTUALIZACIÓN AQUÍ ----
            composable("citas") {
                MisCitasScreen() // Llama directamente a la función
            }
            // ---- FIN DE ACTUALIZACIÓN ----
            composable("turnos") { PlaceholderScreen("Turnos") }
            composable("notificaciones") { PlaceholderScreen("Notificaciones") }
            composable("perfil") { PlaceholderScreen("Perfil") }
        }
    }
}

// 3. NUEVA PANTALLA DE INICIO (HOME)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    // LazyColumn es como un RecyclerView en Compose, ideal para listas que pueden ser largas.
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F7)) // Color de fondo gris claro
    ) {
        // Item 1: Barra superior
        item {
            TopAppBar(
                title = {
                    Text(
                        "Hola, María",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                actions = {
                    IconButton(onClick = { /* Acción para ajustes */ }) {
                        Icon(Icons.Outlined.Settings, contentDescription = "Ajustes")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent // Fondo transparente
                )
            )
        }

        // Item 2: Perfil del paciente
        item {
            PatientProfileCard()
        }

        // Item 3: Tarjeta de acciones rápidas
        item {
            QuickActionsCard()
        }

        // Item 4: Título de Actividad Reciente
        item {
            Text(
                text = "Actividad reciente",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
        }

        // Item 5: Tarjeta de actividad
        item {
            RecentActivityCard()
        }
    }
}

// --- COMPONENTES REUTILIZABLES PARA HomeScreen ---

@Composable
fun PatientProfileCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0E7FF)),
            contentAlignment = Alignment.Center
        ) {
            Text("MP", fontWeight = FontWeight.Bold, color = Color(0xFF4368C9))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text("María Pérez", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
            Text("Paciente registrado", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        }
    }
}

@Composable
fun QuickActionsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            QuickActionItem(icon = Icons.Outlined.CalendarToday, title = "Próximas\ncitas", subtitle = "2 próximas", iconColor = Color.Blue)
            QuickActionItem(icon = Icons.Outlined.FileCopy, title = "Resultados", subtitle = "Últimos disponibles", iconColor = Color.Green)
            QuickActionItem(icon = Icons.Outlined.Notifications, title = "Notificaciones", subtitle = "3 nuevas", iconColor = Color.Blue)
        }
    }
}

@Composable
fun QuickActionItem(icon: ImageVector, title: String, subtitle: String, iconColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = title, tint = iconColor)
        Spacer(modifier = Modifier.height(8.dp))
        Text(title, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(subtitle, color = Color.Gray, textAlign = TextAlign.Center, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun RecentActivityCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Consulta con Dr. López", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text("12 Sep 2025 • Cardiología • Completada", color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// 4. BARRA DE NAVEGACIÓN INFERIOR (BottomNavigationBar)
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("home", "Home", Icons.Filled.Home),
        BottomNavItem("citas", "Citas", Icons.Outlined.CalendarToday),
        BottomNavItem("turnos", "Turnos", Icons.Filled.People),
        BottomNavItem("notificaciones", "Notificaciones", Icons.Outlined.Notifications),
        BottomNavItem("perfil", "Perfil", Icons.Outlined.Person)
    )

    NavigationBar(
        containerColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title, fontSize = 10.sp) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Evita apilar pantallas en la back stack
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF007AFF),
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color(0xFF007AFF),
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

// Clase de datos para los ítems de la barra de navegación
data class BottomNavItem(val route: String, val title: String, val icon: ImageVector)

// Pantalla de relleno para las secciones no construidas
@Composable
fun PlaceholderScreen(title: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Pantalla de $title", style = MaterialTheme.typography.headlineMedium)
    }
}

// --- PREVISUALIZACIONES ---
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    NexoAppTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    NexoAppTheme {
        MainScreen()
    }
}


