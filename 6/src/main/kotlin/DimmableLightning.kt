class DimmableLightning(override val lightsSize: Int) : Lightning {

    private val lights = Array(lightsSize) { IntArray(lightsSize) }

    override fun turnOn(p: Coordinate) {
        lights[p.first][p.second] += 1
    }

    override fun turnOff(p: Coordinate) {
        lights[p.first][p.second] =
            if (lights[p.first][p.second] <= 1) 0 else lights[p.first][p.second] - 1
    }

    override fun toggle(p: Coordinate) {
        lights[p.first][p.second] += 2
    }

    override fun countLights(): Int = lights.fold(0) { acc, column -> acc + column.sum() }

    internal fun isOn(p: Coordinate): Boolean {
        return lights[p.first][p.second] > 0
    }

    internal fun isOff(p: Coordinate): Boolean {
        return !isOn(p)
    }
}
