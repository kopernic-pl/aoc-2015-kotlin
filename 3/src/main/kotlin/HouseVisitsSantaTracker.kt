import com.google.common.io.Resources
import java.io.File

typealias Location = Pair<Int, Int>

object HouseVisitsSantaTracker {

    val zeroLoc = Location(0, 0)

    internal fun moveWithPath(path: List<Location>, c: Char) =
        path + move(path.last(), c)

    internal fun move(loc: Location, c: Char): Location = when (c) {
        '>' -> Location(loc.first + 1, loc.second)
        '<' -> Location(loc.first - 1, loc.second)
        '^' -> Location(loc.first, loc.second + 1)
        'v' -> Location(loc.first, loc.second - 1)
        else -> throw RuntimeException()
    }

    internal fun aoc3a(commands: String): Int =
        commands.fold(listOf(zeroLoc), ::moveWithPath).toSet().size

    internal fun aoc3b(commands: String): Int {
        val startingPos = Pair(listOf(zeroLoc), listOf(zeroLoc))
        return commands
            .foldIndexed(startingPos) { ind, (santaPath, roboSantaPath), c ->
                when (ind % 2) {
                    0 -> {
                        Pair(moveWithPath(santaPath, c), roboSantaPath)
                    }
                    1 -> {
                        Pair(santaPath, moveWithPath(roboSantaPath, c))
                    }
                    else -> throw RuntimeException()
                }
            }.toList()
            .flatten()
            .toSet()
            .size
    }

    fun aoc3a() {
        println(
            "Unique houses visited: ${
            aoc3a(
                File(Resources.getResource("input3").file).bufferedReader().readText()
            )}"
        )
    }

    fun aoc3b() {
        println(
            "Unique houses visited using normal santa and roboSanta: ${
            aoc3b(
                File(Resources.getResource("input3").file).bufferedReader().readText()
            )}"
        )
    }
}

fun main(args: Array<String>) {
    HouseVisitsSantaTracker.aoc3a()
    HouseVisitsSantaTracker.aoc3b()
}
