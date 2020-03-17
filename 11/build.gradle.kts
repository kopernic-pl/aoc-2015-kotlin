plugins {
    application
}

application {
    mainClassName = "SantaPassKt"
}

dependencies {
    implementation("com.google.guava:guava:28.2-jre")
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testCompileOnly("org.junit.jupiter:junit-jupiter-params:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks.test {
    useJUnitPlatform()
}
