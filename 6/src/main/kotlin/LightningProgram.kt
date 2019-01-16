import Lightning.Command

class LightningProgram(commandsProvider: ()->List<String>) {

    val program: Sequence<Pair<Command, Coordinate>>

    init {
        program = commandsProvider()
            .asSequence()
            .map(::parseCommand)
            .flatMap { (command, from, to) ->
                Coordinate.generateRange(from, to).map { Pair(command, it) }
            }
    }

    private fun parseOperation(s: String): Command = when (s) {
        "toggle" -> Command.TOGGLE
        "turn on" -> Command.TURN_ON
        "turn off" -> Command.TURN_OFF
        else -> throw UnrecognizedCommandException()
    }

    private fun parseCoordinate(s: String): Coordinate = s.split(',')
        .map { it.toInt() }
        .let {
            if (it.size != 2) throw UnrecognizedCommandException()
            else Coordinate(it[0], it[1])
        }

    internal fun parseCommand(s: String): Triple<Command, Coordinate, Coordinate> {
        val pattern =
            "(?<operation>turn on|turn off|toggle) (?<from>\\d{1,3},\\d{1,3}) through (?<to>\\d{1,3},\\d{1,3})"
        return Regex(pattern)
            .matchEntire(s)
            ?.destructured
            ?.let { (op, from, to) ->
                Triple(
                    parseOperation(op),
                    parseCoordinate(from),
                    parseCoordinate(to)
                )
            } ?: throw UnrecognizedCommandException()
    }

    class UnrecognizedCommandException : IllegalArgumentException()
}
