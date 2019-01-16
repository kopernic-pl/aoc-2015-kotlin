import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
}

application {
    mainClassName = "NaughtyNiceKt"
}

dependencies {
    compile("com.google.guava:guava:27.0.1-jre")
    compile(kotlin("reflect"))
    testCompile(kotlin("test"))
    testCompile(kotlin("test-junit5"))

    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testCompileOnly("org.junit.jupiter:junit-jupiter-params:5.3.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.2")
}

tasks.test {
    useJUnitPlatform()
}