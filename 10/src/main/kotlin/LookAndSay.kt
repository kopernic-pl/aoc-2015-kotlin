fun main() {
    var input = "1113222113"
    for (i in 1..40) {
        input = lookAndSay(input)
        println("$i: ${input.length}")
    }
    println(input.length)

    for (i in 1..50) {
        input = lookAndSay(input)
        println("$i: ${input.length}")
    }
    println(input.length)
}

fun lookAndSay(s: String): String {
    return s.splitOnChange0().flatMap { listOf(it.length.toString(), it.last().toString()) }.joinToString("")
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

//my own functional impl, but sadly underperforming
fun String.splitOnChange(): List<String> {
    return this.fold(listOf<String>()) { acc, value ->
        if (acc.isEmpty()) acc + listOf(value.toString())
        else {
            val lastGroup = acc.last()
            val lastChar = lastGroup.last()
            if (lastChar != value) acc + listOf(value.toString())
            else acc.dropLast(1) + (lastGroup + value)
        }
    }
}
