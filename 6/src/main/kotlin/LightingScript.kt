import com.google.common.io.Resources
import java.io.File

fun main() {
    aoc6a()
    aoc6b()
}

@Suppress("UnstableApiUsage")
private fun aoc6(lightingSystem: Lighting) {
    println("Program run on ${lightingSystem::class.simpleName} turned on " + lightingSystem
        .apply {
            LightingProgram { File(Resources.getResource("input.txt").toURI()).readLines() }
                .program
                .forEach {(command, coordinate)->
                    this.applyCommand(command, coordinate)
                }
        }
        .countLights()
    + " lights")
}

private fun aoc6a() {
    aoc6(SwitchableLighting(lightsSize = 1000))
}

private fun aoc6b() {
    aoc6(DimmableLighting(lightsSize = 1000))
}
