plugins {
    application
}

application {
    mainClassName = "HouseVisitsSantaTrackerKt"
}

dependencies {
    implementation("com.google.guava:guava:28.2-jre")

    testImplementation(kotlin("test-junit"))
    testCompileOnly("junit:junit:4.13")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.6.0")
}

tasks.test {
    useJUnit()
}
