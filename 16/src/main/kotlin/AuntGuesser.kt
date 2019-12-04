import com.google.common.io.Resources

val facts = mapOf(
    "children" to 3,
    "cats" to 7,
    "samoyeds" to 2,
    "pomeranians" to 3,
    "akitas" to 0,
    "vizslas" to 0,
    "goldfish" to 5,
    "trees" to 3,
    "cars" to 2,
    "perfumes" to 1
)

fun isEqualTo(value: Int): (Int) -> Boolean = { i: Int -> i == value }
fun greater(value: Int): (Int) -> Boolean = { i: Int -> i > value }
fun lower(value: Int): (Int) -> Boolean = { i: Int -> i < value }

val auntFactsCheckers1: Map<String, (Int) -> Boolean> =
    facts.mapValues { (_, fact) -> isEqualTo(fact) }

val auntFactsCheckers2: Map<String, (Int) -> Boolean> =
    auntFactsCheckers1 +
            listOf(
                "cats" to greater(facts["cats"] ?: error("Null value")),
                "pomeranians" to lower(facts["pomeranians"] ?: error("Null value")),
                "goldfish" to lower(facts["goldfish"] ?: error("Null value")),
                "trees" to greater(facts["trees"] ?: error("Null value"))
            )

@Suppress("UnstableApiUsage")
fun main() {
    val auntMemories = Resources.getResource("input.txt")
        .readText().lines()

    findAunt(auntMemories, auntFactsCheckers1, ::println)
    findAunt(auntMemories, auntFactsCheckers2, ::println)
}

internal fun findAunt(
    auntMemories: List<String>,
    auntFactsCheckers: Map<String, (Int) -> Boolean>,
    auntsConsumer: (List<AuntMemory>) -> Unit
) {
    val matchingAunts = AuntMemoriesReader.readMemories(auntMemories)
        .filter { (_, memory) -> memory.matches(auntFactsCheckers) }
    auntsConsumer(matchingAunts)
}

private fun Map<String, Int>.matches(factCheckers: Map<String, (Int) -> Boolean>): Boolean {
    return factCheckers.all { (checkName, check) ->
        !this.containsKey(checkName) || check(this[checkName] ?: error("Checker error"))
    }
}

data class AuntMemory(val name: String, val memory: Map<String, Int>)

object AuntMemoriesReader {
    private const val sueKey = "sue"
    private const val mem1Key = "mem1Key"
    private const val mem1cntKey = "mem1cntKey"
    private const val mem2Key = "mem2Key"
    private const val mem2cntKey = "mem2cntKey"
    private const val mem3Key = "mem3Key"
    private const val mem3cntKey = "mem3cntKey"

    private val regex =
        ("(?<$sueKey>Sue \\d+):" +
                " (?<$mem1Key>\\w+): (?<$mem1cntKey>\\d+)," +
                " (?<$mem2Key>\\w+): (?<$mem2cntKey>\\d+)," +
                " (?<$mem3Key>\\w+): (?<$mem3cntKey>\\d+)").toRegex()

    fun readMemories(s: List<String>): List<AuntMemory> {
        return s.mapNotNull { l -> regex.matchEntire(l) }
            .map { it.groups }
            .map { g ->
                AuntMemory(
                    g[sueKey]!!.value,
                    mapOf(
                        g[mem1Key]!!.value to g[mem1cntKey]!!.value.toInt(),
                        g[mem2Key]!!.value to g[mem2cntKey]!!.value.toInt(),
                        g[mem3Key]!!.value to g[mem3cntKey]!!.value.toInt()
                    )
                )
            }.toList()
    }
}
