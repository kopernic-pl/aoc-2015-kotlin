plugins {
    id("nebula.kotlin") version "1.3.11" apply false
    id("io.gitlab.arturbosch.detekt") version "1.0.0-RC12" apply false

    id("com.github.ben-manes.versions") version "0.20.0"
}

allprojects {
    group = "net.wrony.kopernic"
    version = "1.0-SNAPSHOT"
}

subprojects {
    apply(plugin = "nebula.kotlin")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    repositories {
        jcenter()
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "5.1"
    distributionType = Wrapper.DistributionType.ALL
}