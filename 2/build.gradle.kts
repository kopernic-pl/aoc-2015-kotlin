plugins {
    application
}

application {
    mainClassName = "GiftBoxKt"
}

dependencies {
    implementation("com.google.guava:guava:28.1-jre")
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))

    testCompileOnly("junit:junit:4.12")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.5.2")
}

tasks.test {
    useJUnit()
}
