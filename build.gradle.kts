plugins {
    id("nebula.kotlin") version "1.3.70" apply false
    id("nebula.project") version "7.0.7" apply false
    id("io.gitlab.arturbosch.detekt") version "1.7.0-beta2" apply false

    id("com.github.ben-manes.versions") version "0.28.0"
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
    gradleVersion = "6.2.2"
    distributionType = Wrapper.DistributionType.ALL
}
