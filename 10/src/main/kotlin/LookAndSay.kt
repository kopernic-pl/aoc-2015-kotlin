import kotlin.system.measureTimeMillis

fun main() {
    val input = "1113222113"
    var localInput = input

    for (i in 1..50) {
        val elapsed =measureTimeMillis {
            localInput = lookAndSay(localInput)
        }
        println("$i: ${localInput.length} , $elapsed")
    }
    println(localInput.length)
}

fun lookAndSay(s: String): String {
    return s.splitOnChange().flatMap { listOf(it.length.toString(), it.first().toString()) }.joinToString("")
}

//basing on rosettacode.org implementation of "Split on character change"
fun String.splitOnChange0(): List<String> {
    val out = mutableListOf<String>()
    if (this.isEmpty()) return listOf()
    if (this.length == 1) return listOf(this)
    var currentGroup = this.take(1)

    for (i in 1 until this.length) {
        if (currentGroup.last() == this[i]) currentGroup += this[i]
        else {
            out.add(currentGroup)
            currentGroup = this[i].toString()
        }
    }
    out.add(currentGroup)
    return out
}

//my own functional impl, but sadly underperforming as dropLast copies whole list one by one
fun String.splitOnChange(): List<String> {
    return this.map(Char::toString).fold(listOf<String>()) { acc, value ->
        if (acc.isEmpty()) acc + listOf(value)
        else {
            val lastGroup = acc.last()
            val lastChar = lastGroup.last().toString()
            if (lastChar != value) acc + listOf(value)
            else acc.dropLast(1) + (lastGroup + value)
        }
    }
}
