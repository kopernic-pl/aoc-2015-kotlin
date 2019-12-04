plugins {
    id("nebula.kotlin") version "1.3.61" apply false
    id("nebula.project") version "7.0.4" apply false
    id("io.gitlab.arturbosch.detekt") version "1.2.1" apply false

    id("com.github.ben-manes.versions") version "0.27.0"
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
    gradleVersion = "6.0.1"
    distributionType = Wrapper.DistributionType.ALL
}
