pluginManagement {
    val kotlin_version: String by settings
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://plugins.gradle.org/m2/")
        maven("https://maven.parchmentmc.org")
        maven("https://maven.fabricmc.net/")
        maven("https://repo.spongepowered.org/repository/maven-public/")
        maven("https://maven.neoforged.net/")
        maven("https://maven.architectury.dev")
    }
    resolutionStrategy {
        eachPlugin {
            // If we request Forge, actually give it the correct artifact.
            if (requested.id.id == "net.minecraftforge.gradle") {
                useModule("${requested.id}:ForgeGradle:${requested.version}")
            }
        }
    }
    plugins {
        kotlin("jvm") version kotlin_version
        kotlin("plugin.serialization") version kotlin_version
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "Aeternus"
include(
    "common",
    "fabric",
    "neoforge",
    "forge"
)
