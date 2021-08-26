import com.google.common.io.Resources.getResource

@Suppress("UnstableApiUsage")
fun main() {
    val input = getResource("input.txt").readText().lines()
    val inputMolecule = input.last()
    val allReplacements = input.filterNot(String::isNullOrEmpty) - inputMolecule
    val replacementMap = allReplacements.map(::readReplacement)

    val allPossibleMolecules =
        replacementMap
            .map { (fromAtom, toAtom) ->
                fromAtom.toRegex().findAll(inputMolecule)
                    .map(MatchResult::range)
                    .fold(listOf<String>()) { acc, range -> acc + inputMolecule.replaceRange(range, toAtom) }
            }
            .flatten().toSet()
    println("Calibration: ${allPossibleMolecules.size} combinations found")

    findSmallestNumber(inputMolecule, replacementMap)
}

fun replace(s: String, input: String, out: String, position: Int): String {
    return s.substring(0, position) + out + s.substring(position + input.length)
}

fun findSmallestNumber(targetMolecule: String, allReplacements: List<Pair<String, String>>) {
    var nbOfReplaces: Int
    do {
        println("New shuffle")
        nbOfReplaces = 0
        var iterations = 0
        var currentMolecule = targetMolecule

        val reps = allReplacements.shuffled()
        val maxIterations = 3333
        while (currentMolecule != "e" && iterations < maxIterations) {
            for ((from, toAtom) in reps) {
                if (currentMolecule.contains(toAtom)) {
                    nbOfReplaces += toAtom.toRegex().findAll(currentMolecule).toList().size
                    currentMolecule = currentMolecule.replace(toAtom, from)
                }
            }
            iterations++
        }
    } while (currentMolecule != "e")
    println(nbOfReplaces)
}

fun readReplacement(replacement: String): Pair<String, String> {
    return replacement.split(" => ").let { it[0] to it[1] }
}
