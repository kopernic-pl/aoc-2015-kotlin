plugins {
    id("nebula.kotlin") version "1.3.31" apply false
    id("nebula.project") version "6.0.3" apply false
    id("io.gitlab.arturbosch.detekt") version "1.0.0-RC14" apply false

    id("com.github.ben-manes.versions") version "0.21.0"
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
    gradleVersion = "5.4.1"
    distributionType = Wrapper.DistributionType.ALL
}
