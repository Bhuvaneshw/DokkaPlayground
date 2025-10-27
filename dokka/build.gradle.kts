plugins {
    alias(libs.plugins.kotlin.dokka)
}

/*
 * allow custom properties from cli arg. helpful for CI/CD
 * sample cmd: ./gradlew dokkaGenerate -Prelease-version=1.0.0 -PdocsDir=build/docs
 */
val apiVersion: String = if (project.hasProperty("release-version")) {
    project.property("release-version")!!.toString()
} else {
    project.version.toString()
}
val dokkaVersionsDirectory = run {
    val outputDirectory = providers.gradleProperty("docsDir").orNull
    return@run outputDirectory?.let(rootDir::resolve)
}

dokka {
    moduleName = "DokkaPlayground"

    pluginsConfiguration {
        versioning {
            version = apiVersion
            if (dokkaVersionsDirectory != null) olderVersionsDir = dokkaVersionsDirectory
            // TODO: set olderVersionsDirName to empty string
//            olderVersionsDirName = ""
//            waiting for this property to be release in dokka
//            property is in source but not yet released (checked v: 2.1.0)
            renderVersionsNavigationOnAllPages = true
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

dependencies {
    // include all modules that's need to be documented
    dokka(project(":core"))
    dokka(project(":ui"))

    dokkaHtmlPlugin(libs.dokka.versioning.plugin)
}
