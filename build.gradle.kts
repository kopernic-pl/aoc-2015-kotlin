plugins {
    id("nebula.kotlin") version "1.3.21" apply false
    id("nebula.project") version "6.0.1" apply false
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

tasks.withType<Wrapper> {
    gradleVersion = "5.3.1"
    distributionType = Wrapper.DistributionType.ALL
}
