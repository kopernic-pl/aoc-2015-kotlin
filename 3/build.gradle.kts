plugins {
    application
}

application {
    mainClass.set("HouseVisitsSantaTrackerKt")
}

dependencies {
    implementation("com.google.guava:guava:31.0.1-jre")

    testImplementation(kotlin("test-junit"))
    testCompileOnly("junit:junit:4.13.2")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.8.1")
}

tasks.test {
    useJUnit()
}
