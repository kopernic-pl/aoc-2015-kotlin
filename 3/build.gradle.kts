import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
}

application {
    mainClassName = "HouseVisitsSantaTrackerKt"
}

dependencies {
    compile("com.google.guava:guava:27.1-jre")
    compile(kotlin("reflect"))
    testCompile(kotlin("test"))
    testCompile(kotlin("test-junit"))

    testCompileOnly("junit:junit:4.12")
    testRuntime("org.junit.vintage:junit-vintage-engine:5.5.0")
}

tasks.test {
    useJUnit()
}