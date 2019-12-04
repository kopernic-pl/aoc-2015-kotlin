import com.google.common.io.Resources

private const val NB_OF_ITERATIONS = 100

@Suppress("UnstableApiUsage")
fun main() {
    val lights = Resources.getResource("input.txt")
        .readText().lines()
    require(lights.all { line -> line.length == lights.size }) { "Input should be square" }

    println("All lights are working (pt1)")
    calculateField(lights, NB_OF_ITERATIONS)

    println("Corner lights are stuck (pt2)")
    val lightsStuckOn = listOf(0 to 0, 0 to lights.size - 1, lights.size - 1 to lights.size - 1, lights.size - 1 to 0)
    calculateField(lights, NB_OF_ITERATIONS, lightsStuckOn)
}

private fun calculateField(
    lights: List<String>,
    numberOfIterations: Int,
    lightsStuckOn: List<Pair<Int, Int>> = listOf()
) {
    val f = Field(lights.size, lightsStuckOn) { i, j ->
        when (lights[i][j]) {
            '.' -> false
            '#' -> true
            else -> throw IllegalArgumentException()
        }
    }
    repeat(numberOfIterations) {
        f.calcNextState()
        f.switch()
    }
    println("After $numberOfIterations steps there are ${f.numberOfOnLights()} lights on")
}
