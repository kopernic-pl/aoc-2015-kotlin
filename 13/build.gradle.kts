plugins {
    application
}

application {
    mainClass.set("TableSittingKt")
}

dependencies {
    implementation("com.google.guava:guava:31.0.1-jre")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.amshove.kluent:kluent:1.68")
    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testCompileOnly("org.junit.jupiter:junit-jupiter-params:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}
