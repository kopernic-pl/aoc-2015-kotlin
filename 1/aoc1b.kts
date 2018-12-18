import java.io.File
import kotlin.system.exitProcess


run loop@{
    var floor = 0
    File("input1")
            .reader().readText()
            .forEachIndexed { commandIdx, c ->
                when {
                    c.equals('(') -> floor++
                    c.equals(')') -> floor--
                    else -> throw RuntimeException("funny value in input")
                }
                if (floor < 0) {
                    println("Command index for going to basement: ${commandIdx + 1}")
                    return@loop //funny way to break in Kotlin, indeed
                }
            }
}