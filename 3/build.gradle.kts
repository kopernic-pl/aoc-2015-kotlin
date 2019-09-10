plugins {
    application
}

application {
    mainClassName = "HouseVisitsSantaTrackerKt"
}

dependencies {
    implementation("com.google.guava:guava:28.1-jre")
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    testCompileOnly("junit:junit:4.12")
    testRuntime("org.junit.vintage:junit-vintage-engine:5.5.2")
}

tasks.test {
    useJUnit()
}
