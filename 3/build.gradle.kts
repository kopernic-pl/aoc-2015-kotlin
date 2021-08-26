plugins {
    application
}

application {
    mainClass.set("HouseVisitsSantaTrackerKt")
}

dependencies {
    implementation("com.google.guava:guava:30.1.1-jre")

    testImplementation(kotlin("test-junit"))
    testCompileOnly("junit:junit:4.13.2")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.7.2")
}

tasks.test {
    useJUnit()
}
