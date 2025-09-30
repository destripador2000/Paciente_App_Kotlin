# Documentación del Proyecto Nexo - Aplicación Móvil

## Descripción General

Nexo es una aplicación móvil desarrollada en Android con Kotlin y Jetpack Compose, diseñada para pacientes del sistema de salud pública de Nicaragua. La aplicación permite a los usuarios gestionar sus citas médicas, ver turnos en tiempo real, recibir notificaciones y acceder a su información personal de manera eficiente.

## Estructura del Proyecto

```
NexoApp/
├── app/                          # Módulo principal de la aplicación
│   ├── src/main/
│   │   ├── java/com/example/nexoapp/
│   │   │   ├── CitaMedica.kt              # Modelo de datos para citas
│   │   │   ├── Login.kt                   # Pantallas de autenticación
│   │   │   ├── MainActivity.kt            # Actividad principal
│   │   │   ├── MisCitasScreen.kt          # Pantalla de gestión de citas
│   │   │   ├── MisTurnosScreen.kt         # Pantalla de turnos
│   │   │   ├── NotificacionesScreen.kt    # Pantalla de notificaciones
│   │   │   ├── Principal.kt               # Navegación principal
│   │   │   └── ui/theme/                  # Sistema de temas y estilos
│   │   │       ├── Color.kt
│   │   │       ├── Theme.kt
│   │   │       └── Type.kt
│   │   ├── res/                          # Recursos
│   │   │   ├── drawable/                 # Imágenes y vectores
│   │   │   ├── values/                   # Colores, strings, temas
│   │   │   └── xml/                      # Configuraciones
│   ├── build.gradle.kts                 # Configuración del módulo
│   └── proguard-rules.pro               # Reglas de ofuscación
├── gradle/
│   └── libs.versions.toml               # Versiones de dependencias
├── build.gradle.kts                     # Configuración del proyecto
├── settings.gradle.kts                  # Configuración de módulos
└── gradlew                             # Scripts de Gradle
```

## Tecnologías y Versiones

### Versiones Principales
- **Android Gradle Plugin**: 8.13.0
- **Kotlin**: 2.2.20
- **Compile SDK**: 36
- **Min SDK**: 29 (Android 10)
- **Target SDK**: 36
- **Jetpack Compose BOM**: 2025.09.01

### Dependencias Principales
```kotlin
dependencies {
    implementation("androidx.compose.material:material-icons-extended:1.6.7")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
}
```

## Arquitectura y Navegación

### Sistema de Navegación
La aplicación utiliza Navigation Compose con la siguiente estructura:

```
WelcomeScreen
    ↓
LoginScreen
    ↓
MainScreen (con BottomNavigationBar)
    ├── HomeScreen
    ├── MisCitasScreen
    ├── MisTurnosScreen
    ├── NotificacionesScreen
    └── Perfil (Placeholder)
```

### Componentes Principales

#### 1. **Principal.kt** - Navegación Principal
```kotlin
@Composable
fun PrincipalAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "welcome") {
        composable("welcome") { PrincipalWelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("main") { MainScreen() }
    }
}
```

#### 2. **MainScreen.kt** - Pantalla Principal con Bottom Navigation
```kotlin
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(navController, "home") {
            composable("home") { HomeScreen() }
            composable("citas") { MisCitasScreen() }
            composable("turnos") { MisTurnosScreen() }
            composable("notificaciones") { NotificacionesScreen() }
            composable("perfil") { PlaceholderScreen("Perfil") }
        }
    }
}
```

## Pantallas y Funcionalidades

### 1. **Pantalla de Bienvenida (WelcomeScreen)**
- Presentación de la aplicación
- Ilustración de hospital
- Botón de inicio de sesión
- Opción de registro

### 2. **Pantalla de Login (LoginScreen)**
- Campos para correo/cédula y contraseña
- Validación básica de formularios
- Opción de recuperación de contraseña
- Navegación a pantalla principal

### 3. **Pantalla de Inicio (HomeScreen)**
- Saludo personalizado al usuario
- Perfil del paciente
- Acciones rápidas (citas, resultados, notificaciones)
- Actividad reciente

### 4. **Gestión de Citas (MisCitasScreen)**
- Lista de citas médicas con estados
- Información detallada (doctor, especialidad, fecha, hora)
- Estados: CONFIRMADA, PENDIENTE, CANCELADA
- Funcionalidad de recordatorios

### 5. **Sistema de Turnos (MisTurnosScreen)**
- Número actual de turno
- Posición del usuario en la cola
- Tiempo estimado de espera
- Consejos para pacientes

### 6. **Notificaciones (NotificacionesScreen)**
- Lista de notificaciones organizadas
- Diferentes tipos: resultados, recordatorios, mensajes
- Icono de ajustes para configuración

## Modelos de Datos

### Cita Médica
```kotlin
data class CitaMedica(
    val id: String,
    val doctorNombre: String,
    val especialidad: String,
    val fecha: String,
    val hora: String,
    val ubicacion: String,
    val estado: EstadoCita,
    val notasAdicionales: String? = null
)

enum class EstadoCita(val displayName: String, val color: Color) {
    CONFIRMADA("CONFIRMADA", Color(0xFF4CAF50)),
    PENDIENTE("PENDIENTE", Color.Gray),
    CANCELADA("CANCELADA", Color.Red)
}
```

### Notificación
```kotlin
data class NotificacionItem(
    val id: String = UUID.randomUUID().toString(),
    val titulo: String,
    val subtitulo: String,
    val tipo: TipoNotificacion = TipoNotificacion.INFO
)

enum class TipoNotificacion {
    RESULTADO, RECORDATORIO, MENSAJE, INFO
}
```

## Estilos y Temas

### Sistema de Diseño
- **Tema Principal**: Material Design 3
- **Colores**: Esquema de colores dinámicos (Android 12+)
- **Tipografía**: Fuentes predeterminadas del sistema
- **Iconografía**: Material Icons y recursos personalizados

### Configuración del Tema
```kotlin
@Composable
fun NexoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Implementación del tema con colores dinámicos
}
```

## Configuración de Build

### Gradle Properties
```properties
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
android.useAndroidX=true
kotlin.code.style=official
android.nonTransitiveRClass=true
```

### Configuración del Módulo App
```kotlin
android {
    namespace = "com.example.nexoapp"
    compileSdk = 36
    
    defaultConfig {
        applicationId = "com.example.nexoapp"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
    
    buildFeatures {
        compose = true
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    
    kotlinOptions {
        jvmTarget = "11"
    }
}
```

## Características Técnicas

### 1. **Compatibilidad**
- Mínimo: Android 10 (API 29)
- Recomendado: Android 12+ (para colores dinámicos)

### 2. **Arquitectura**
- Jetpack Compose para UI
- Navigation Compose para navegación
- Estado manejado con `mutableStateOf`
- Arquitectura MVVM implícita

### 3. **Rendimiento**
- LazyColumn para listas largas
- Recomposición eficiente
- Imágenes optimizadas con Coil

### 4. **Accesibilidad**
- Content descriptions en todos los elementos interactivos
- Contraste de colores adecuado
- Tamaños de texto escalables

## Comandos de Desarrollo

### Build y Ejecución
```bash
# Instalar dependencias
./gradlew build

# Ejecutar en emulador/dispositivo
./gradlew installDebug

# Ejecutar pruebas
./gradlew test
./gradlew connectedAndroidTest
```

### Limpieza
```bash
# Limpiar proyecto
./gradlew clean

# Rebuild completo
./gradlew clean build
```

## Estructura de Recursos

### Drawables
- `hospital_illustration`: Ilustración principal
- `ic_launcher_foreground/background`: Iconos de la aplicación
- Recursos adaptativos para diferentes densidades

### Valores
- `colors.xml`: Paleta de colores legacy
- `strings.xml`: Cadenas de texto
- `themes.xml`: Temas XML legacy

