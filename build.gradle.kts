import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("nebula.project") version "9.1.6" apply false
    id("com.diffplug.spotless") version "6.0.0" apply false
    id("com.github.ben-manes.versions") version "0.39.0"
    kotlin("jvm") version "1.5.31" apply false
}

allprojects {
    group = "net.wrony.kopernic"
    version = "1.0-SNAPSHOT"
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("nebula.project")
        plugin("com.diffplug.spotless")
    }

    configure<KotlinJvmProjectExtension> {
        jvmToolchain {
            (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(16))
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        val implementation by configurations
        implementation(kotlin("stdlib-jdk8"))
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            ktlint()
        }
        kotlinGradle {
            target("*.gradle.kts")
            ktlint()
        }
    }
}

tasks {
    wrapper {
        gradleVersion = "7.3"
        distributionType = Wrapper.DistributionType.ALL
    }
}
