plugins {
    application
}

application {
    mainClassName = "TableSittingKt"
}

dependencies {
    compile("com.google.guava:guava:27.1-jre")
    compile(kotlin("reflect"))
    testCompile(kotlin("test"))
    testCompile(kotlin("test-junit5"))

    testImplementation("org.amshove.kluent:kluent:1.52")
    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.5.0")
    testCompileOnly("org.junit.jupiter:junit-jupiter-params:5.5.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.0")
}

tasks.test {
    useJUnitPlatform()
}
