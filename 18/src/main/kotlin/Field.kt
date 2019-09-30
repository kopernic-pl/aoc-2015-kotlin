import Light.Companion.byBoolean

class Field(
    internal val size: Int,
    lightsStuckOn: List<Pair<Int, Int>> = listOf(),
    init: (Int, Int) -> Boolean = { _, _ -> false }
) {
    private val field: Array<Array<Light>>
    private val allLights: List<Light>
    private val lightsStuckOn: List<Light>

    init {
        this.field = Array(size) { p -> Array(size) { q -> byBoolean(init(p, q)) } }
        this.allLights = field.flatten()
        this.lightsStuckOn = initStuckOn(lightsStuckOn)
        initFieldNeighbours()
    }

    private fun initStuckOn(lightsStuckOn: List<Pair<Int, Int>>): List<Light> {
        lightsStuckOn.forEach { (p, q) -> field[p][q].state = true }
        return lightsStuckOn.map(::fieldByLocation)
    }

    private fun initFieldNeighbours() {
        field.indices.flatMap { x -> field.indices.map { y -> x to y } }
            .map { lightLoc -> fieldByLocation(lightLoc) to getNeighbours(lightLoc) }
            .forEach { (light, neighbours) -> light.neighbours = neighbours }
    }

    private fun getNeighbours(loc: Pair<Int, Int>): List<Light> {
        val (x, y) = loc
        val xes = listOf(x - 1, x, x + 1).filter(this::isInField)
        val ys = listOf(y - 1, y, y + 1).filter(this::isInField)
        return (xes.flatMap { locX -> ys.map { locY -> locX to locY } } - Pair(x, y)).map { fieldByLocation(it) }
    }

    internal fun numberOfLights(): Int {
        return field.map { row -> row.size }.sum()
    }

    internal fun numberOfOnLights(): Int {
        return field.map { row -> row.filter { l -> l.state }.size }.sum()
    }

    internal fun fieldByLocation(loc: Pair<Int, Int>): Light {
        val (x, y) = loc
        return field[x][y]
    }

    internal fun fieldByLocation(x: Int, y: Int): Light {
        return field[x][y]
    }

    private fun isInField(it: Int) = it >= 0 && it < field.size

    fun calcNextState() {
        (allLights - lightsStuckOn).forEach(Light::calculateNextState)
    }

    fun switch() {
        (allLights - lightsStuckOn).forEach(Light::switchState)
    }
}
