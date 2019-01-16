class SwitchableLightning(override val lightsSize: Int) : Lightning {

    private val lights = Array(lightsSize) { BooleanArray(lightsSize) }

    override fun turnOn(p: Coordinate) {
        lights[p.first][p.second] = true
    }

    override fun turnOff(p: Coordinate) {
        lights[p.first][p.second] = false
    }

    override fun toggle(p: Coordinate) {
        lights[p.first][p.second] = !lights[p.first][p.second]
    }

    override fun countLights(): Int =
        lights.fold(0) { acc, column -> acc + column.count { it == true } }

    internal fun isOn(p: Coordinate): Boolean {
        return lights[p.first][p.second]
    }

    internal fun isOff(p: Coordinate): Boolean {
        return !isOn(p)
    }
}
