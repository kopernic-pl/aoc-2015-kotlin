import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("nebula.kotlin") version "1.3.11"
}

group = "net.wrony.kopernic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Wrapper> {
    gradleVersion = "5.0"
	distributionType = Wrapper.DistributionType.ALL
}