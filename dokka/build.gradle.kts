import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.dokka)
}

val apiVersion: String = if (project.hasProperty("release-version")) {
    project.property("release-version")!!.toString()
} else {
    project.version.toString()
}
val dokkaVersionsDirectory = resolveVersionsDirectory()

dokka {
    moduleName = "DokkaPlayground"

    pluginsConfiguration {
        versioning {
            version = apiVersion
            if (dokkaVersionsDirectory != null) olderVersionsDir = dokkaVersionsDirectory
        }
        pluginsConfiguration.html {
            footerMessage.set("By Bhuvaneshwaran")
        }
    }

    dokkaPublications.html {
        if (dokkaVersionsDirectory != null)
            outputDirectory = dokkaVersionsDirectory.resolve(apiVersion)
    }
}

android {
    namespace = "com.bhuvaneshw.dokkaplayground.dokka"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    dokka(project(":core"))
    dokka(project(":ui"))
    dokkaHtmlPlugin(libs.dokka.versioning.plugin)
}

fun resolveVersionsDirectory(): File? {
    val outputDirectory = providers.gradleProperty("docsDir").orNull
    return outputDirectory?.let(rootDir::resolve)
}
