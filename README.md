
# Documentación del Proyecto Nexo - Aplicación Móvil

## Descripción General

Nexo es una aplicación móvil desarrollada en **Android con Kotlin y Jetpack Compose**, diseñada para pacientes del sistema de salud pública de Nicaragua.
La aplicación permite a los usuarios:

* Gestionar citas médicas
* Ver turnos en tiempo real
* Recibir notificaciones
* Acceder a su información personal de forma segura y eficiente

---

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

---

## Tecnologías y Versiones

* **Android Gradle Plugin**: 8.13.0
* **Kotlin**: 2.2.20
* **Compile SDK**: 36
* **Min SDK**: 29 (Android 10)
* **Target SDK**: 36
* **Jetpack Compose BOM**: 2025.09.01

---

## Pasos para Instalar y Ejecutar el Proyecto

### 1. **Requisitos Previos**

Antes de instalar el proyecto, asegúrate de tener lo siguiente:

* [Android Studio Iguana+ (o más reciente)](https://developer.android.com/studio)
* **JDK 17 o superior**
* **Gradle** (incluido en el proyecto con `gradlew`)
* Un **emulador de Android** (API ≥ 29) o un **dispositivo físico con Android 10+**
* [Git](https://git-scm.com/) para clonar el repositorio

Verifica las versiones instaladas:

```bash
java -version
git --version
```

---

### 2. **Clonar el Repositorio**

```bash
git clone https://github.com/destripador2000/Paciente_App_Kotlin.git
cd Paciente_App_Kotlin
```

---

### 3. **Abrir el Proyecto en Android Studio**

1. Abre **Android Studio**
2. Selecciona **File → Open…**
3. Busca la carpeta clonada (`nexo-app`) y ábrela
4. Android Studio descargará automáticamente las dependencias y sincronizará el proyecto

---

### 4. **Configurar el SDK y Emulador**

1. Abre **SDK Manager** en Android Studio
2. Instala el **Android SDK 36 (API Level 36)** y las herramientas de compilación recomendadas
3. Crea un emulador:

   * **AVD Manager → Create Virtual Device**
   * Selecciona un dispositivo (por ejemplo, Pixel 6)
   * Instala la imagen del sistema **Android 13 (API 33)** o superior

---

### 5. **Compilar y Ejecutar la Aplicación**

Desde la terminal del proyecto:

```bash
# Compilar el proyecto
./gradlew build

# Ejecutar en un emulador o dispositivo conectado
./gradlew installDebug
```

O directamente desde Android Studio:

* Selecciona el emulador o dispositivo físico en la barra superior
* Haz clic en el botón **Run ▶️**

---

### 6. **Ejecutar Pruebas**

```bash
# Pruebas locales
./gradlew test

# Pruebas instrumentadas en dispositivo/emulador
./gradlew connectedAndroidTest
```

---

### 7. **Limpieza y Reconstrucción**

Si encuentras errores de build o conflictos:

```bash
# Limpiar el proyecto
./gradlew clean

# Reconstruir desde cero
./gradlew clean build
```

---

## Arquitectura y Navegación

La app utiliza **Navigation Compose** con la siguiente estructura:

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

---

## Configuración de Build

En `app/build.gradle.kts`:

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

    kotlinOptions {
        jvmTarget = "11"
    }
}
```

---

## Comandos Rápidos

| Acción                 | Comando                          |
| ---------------------- | -------------------------------- |
| Compilar               | `./gradlew build`                |
| Instalar en emulador   | `./gradlew installDebug`         |
| Ejecutar pruebas       | `./gradlew test`                 |
| Pruebas instrumentadas | `./gradlew connectedAndroidTest` |
| Limpiar proyecto       | `./gradlew clean`                |
| Reconstrucción total   | `./gradlew clean build`          |

---

## Notas Finales

* Se recomienda utilizar un **emulador con Android 13 o superior** para soportar colores dinámicos (Material You).
* Asegúrate de habilitar la **Depuración USB** si usas un dispositivo físico.
* El proyecto está listo para escalar y conectarse a APIs REST para datos en tiempo real.

