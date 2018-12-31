import java.io.File
import java.io.InputStreamReader

findBasementCommandIndex(File("input1").reader().readText())

fun findBasementCommandIndex(command: String): Int {
    return command.foldIndexed(0, ::processCommand)
}

fun processCommand(commandIdx: Int, floor: Int, command: Char): Int {

    val nextFloor= when {
        command.equals('(') -> floor + 1
        command.equals(')') -> floor - 1
        else -> throw RuntimeException("funny value in input")
    }
    println("${commandIdx+1} : floor $floor, going to $nextFloor")
    if (nextFloor<0) throw RuntimeException("Going to basement on idx ${commandIdx+1}")
    return nextFloor;
}