import com.google.common.io.Resources

@Suppress("UnstableApiUsage")
fun main() {
    val input = Resources.getResource("input.json").readText()

    println(Accounting(input).addAllNumbers())
    println(Accounting(Reformatter().reformat(input)).addAllNumbers())
}

class Accounting(s: String) {
    private val accountingDoc = s.replace("\\s".toRegex(), "")

    companion object {
        val reg = """(?<a>-?\d+)[,}\]]""".toRegex()
    }

    fun addAllNumbers(): Int {
        return reg.findAll(accountingDoc).map { it.groups["a"]?.value?.toIntOrNull() }.filterNotNull().sum()
    }
}

class Reformatter {
    companion object {
        val red = """:"red"""".toRegex()
        val whitespace = "\\s".toRegex()
    }

    private fun findAllRed(s: String): List<IntRange> {
        return red.findAll(s).map { it.range }.toList()
    }

    tailrec fun reformat(s: String): String {
        val doc = s.replace(whitespace, "")
        val allReds = findAllRed(doc)
        if (allReds.isEmpty()) return doc

        val lastRed = allReds.last()

        val startToCut = doc.findSameLevelLeadingOpen(lastRed.start)
        val endToCut = doc.findMatchingTrailingClose(startToCut)
        return reformat(doc.removeRange(startToCut + 1, endToCut))
    }

    private fun String.findSameLevelLeadingOpen(op: Int): Int {
        var currentlyOpenedObjects = 1
        var pos = op
        do {
            val c = this[pos]
            if (c == '{') currentlyOpenedObjects--
            if (c == '}') currentlyOpenedObjects++
            pos--
        } while (currentlyOpenedObjects > 0)
        return pos + 1
    }

    private fun String.findMatchingTrailingClose(op: Int): Int {
        var currentlyOpenedObjects = 0
        var pos = op
        do {
            val c = this[pos]
            if (c == '{') currentlyOpenedObjects++
            if (c == '}') currentlyOpenedObjects--
            pos++
        } while (currentlyOpenedObjects > 0)
        return pos - 1
    }
}
