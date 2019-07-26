import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
}

application {
    mainClassName = "GiftBoxKt"
}

dependencies {
    compile("com.google.guava:guava:28.0-jre")
    compile(kotlin("reflect"))
    testCompile(kotlin("test"))
    testCompile(kotlin("test-junit"))

    testCompileOnly("junit:junit:4.12")
    testRuntime("org.junit.vintage:junit-vintage-engine:5.5.1")
}

tasks.test {
    useJUnit()
}