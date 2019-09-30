plugins {
    id("nebula.kotlin") version "1.3.50" apply false
    id("nebula.project") version "6.2.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.0.1" apply false

    id("com.github.ben-manes.versions") version "0.25.0"
}

allprojects {
    group = "net.wrony.kopernic"
    version = "1.0-SNAPSHOT"
}

subprojects {
    apply(plugin = "nebula.kotlin")
    apply(plugin = "nebula.project")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    repositories {
        jcenter()
    }
}

tasks.wrapper {
    gradleVersion = "5.6.2"
    distributionType = Wrapper.DistributionType.ALL
}
