import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("nebula.project") version "8.2.0" apply false
    id("com.diffplug.spotless") version "5.14.3" apply false
    id("com.github.ben-manes.versions") version "0.39.0"
    kotlin("jvm") version "1.5.30" apply false
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
            jvmTarget = "16"
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
        gradleVersion = "7.2"
        distributionType = Wrapper.DistributionType.ALL
    }
}
