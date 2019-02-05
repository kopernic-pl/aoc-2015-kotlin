import com.google.common.io.Resources
import java.io.File

fun main() {
    fun8()
}

fun fun8() {
    val lines = File(Resources.getResource("input.txt").toURI()).readLines()
    val codeSize = lines.map(String::length).sum()
    val memSize = lines.map(String::transformToMem).map { it.length }.sum()

    println("Code size: $codeSize, mem size: $memSize diff: ${codeSize - memSize}")

    val escapedSize = lines.map(String::escape).map(String::length).sum()

    println("Code size: $codeSize, escaped size: $escapedSize diff: ${escapedSize - codeSize}")
}

fun String.transformToMem(): String {
    return this
        .removePrefix("\"")
        .removeSuffix("\"")
        .replace("\\\\", "\\")
        .replace("\\\"", "\"")
        .replace("\\\\x[0-9a-f]{2}".toRegex(), "R")
}

fun String.escape(): String {
    return this.map {
        when (it) {
            '\"' -> "\\\""
            '\\' -> "\\\\"
            else -> it.toString()
        }
    }.joinToString(separator = "", prefix = "\"", postfix = "\"")
}
