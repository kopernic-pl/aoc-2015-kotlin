interface Lightning {
    val lightsSize: Int

    enum class Command {
        TURN_ON,
        TURN_OFF,
        TOGGLE
    }

    fun applyCommand(command: Command, coordinate: Coordinate) {
        when (command) {
            Command.TOGGLE -> toggle(coordinate)
            Command.TURN_OFF -> turnOff(coordinate)
            Command.TURN_ON -> turnOn(coordinate)
        }
    }

    fun turnOn(p: Coordinate)
    fun turnOff(p: Coordinate)
    fun toggle(p: Coordinate)

    fun countLights(): Int
}
