import com.google.common.collect.Sets.combinations

internal val containers = """
    33
    14
    18
    20
    45
    35
    16
    35
    1
    13
    18
    13
    50
    44
    48
    6
    24
    41
    30
    42
""".trimIndent().lines().map { it.toInt() }

internal const val ExpectedVolume = 150

internal fun generateCombinationsOfIndices(numberOfElements: Int): List<Set<Int>> {
    val indices = (0 until numberOfElements).toSet()
    return (1..numberOfElements).flatMap { size -> combinations(indices, size) }
}

internal fun indexesToSumOfContainerVolumes(indices: Set<Int>): Int {
    return indices.fold(0) { acc, idx -> acc + containers[idx] }
}

fun main() {
    generateCombinationsOfIndices(containers.size)
        .map(::indexesToSumOfContainerVolumes)
        .filter { it == ExpectedVolume }
        .also { println("Number of combinations that holds $ExpectedVolume liters: ${it.size}") }

    val m = generateCombinationsOfIndices(containers.size)
        .map { c -> c to indexesToSumOfContainerVolumes(c) }
        .filter { (_, size) -> size == ExpectedVolume }
        .map { (set, _) -> set to set.size }
        .toMap()

    m.filterValues { it == m.values.min() }
        .also { println("Number of combinations with smallest (${m.values.min()}) number of containers: ${it.size}") }
}
