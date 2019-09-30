plugins {
    application
}

application {
    mainClassName = "EggNogContainersKt"
}

dependencies {
    implementation("com.google.guava:guava:28.1-jre")
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.amshove.kluent:kluent:1.56")
    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testCompileOnly("org.junit.jupiter:junit-jupiter-params:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
}

tasks.test {
    useJUnitPlatform()
}
