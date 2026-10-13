plugins {
    // IMPORTANTE: troque 4.0.0 pela versão exata que veio no build.gradle.kts gerado pelo Initializr
    id("org.springframework.boot") version "4.1.1" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
}

// NOVO: configuração comum a todos os módulos
allprojects {
    group = "dev.pixsim"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }
}

// NOVO: Java 25 e JUnit para os 3 módulos
subprojects {
    apply(plugin = "java")

    extensions.configure<JavaPluginExtension> {
        toolchain {
            languageVersion = JavaLanguageVersion.of(25)
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
