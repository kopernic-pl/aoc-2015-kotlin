plugins {
    application
}

application {
    mainClass.set("Accounting")
}

dependencies {
    implementation("com.google.guava:guava:30.1.1-jre")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.amshove.kluent:kluent:1.68")
    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testCompileOnly("org.junit.jupiter:junit-jupiter-params:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
}

tasks.test {
    useJUnitPlatform()
}
