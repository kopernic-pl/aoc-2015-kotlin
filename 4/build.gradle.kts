import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
}

application {
    mainClassName = "SantaCoinKt"
}

dependencies {
    compile("com.google.guava:guava:27.1-jre")
    compile(kotlin("reflect"))
    testCompile(kotlin("test"))
    testCompile(kotlin("test-junit5"))

    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.4.0")
    testCompileOnly("org.junit.jupiter:junit-jupiter-params:5.4.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.0")
}

tasks.test {
    useJUnitPlatform()
}