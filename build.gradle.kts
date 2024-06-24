plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.google.services) apply false
}

// No es necesario asignar rootProject.name aquí, ya se hace en settings.gradle.kts

// Eliminar la línea `include(":app")` ya que también está en settings.gradle.kts
