import com.google.common.io.Resources
import java.io.File

fun main(args: Array<String>) {
    aoc6a()
    aoc6b()
}

private fun aoc6(lightningSystem: Lightning) {
    println("Program run on ${lightningSystem::class.simpleName} turned on " + lightningSystem
        .apply {
            LightningProgram { File(Resources.getResource("input.txt").toURI()).readLines() }
                .program
                .forEach {(command, coordinate)->
                    this.applyCommand(command, coordinate)
                }
        }
        .countLights()
    + " lights")
}

private fun aoc6a() {
    aoc6(SwitchableLightning(lightsSize = 1000))
}

private fun aoc6b() {
    aoc6(DimmableLightning(lightsSize = 1000))
}
