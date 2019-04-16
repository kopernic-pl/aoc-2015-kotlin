import com.google.common.collect.Collections2
import com.google.common.io.Resources

fun main() {
    val input = Resources.getResource("input.txt").readText()

    val bestSittingWithoutMe = SittingPlan().findBestSitting(SittingPreferences(input))
    println(bestSittingWithoutMe)

    val bestSittingWithMe = SittingPlan().findBestSitting(SittingPreferences(input).withMyself())
    println(bestSittingWithMe)
}

class SittingPreferences {
    constructor(preferences: String) {
        preferencesMap = preferences.lineSequence().map(::parsePreferenceLine).toMap()
    }

    constructor(preferences: Map<Pair<String, String>, Int>) {
        preferencesMap = preferences
    }

    val preferencesMap: Map<Pair<String, String>, Int>

    fun getPreference(name1: String, name2: String): Int? = preferencesMap[name1 to name2]

    fun getPreference(p: Pair<String, String>): Int? = preferencesMap[p.first to p.second]

    fun getAllNames(): Set<String> = preferencesMap.keys.flatMap { it.toList() }.toSet()

    fun withMyself(): SittingPreferences {
        val relationsToMe = getAllNames().flatMap { listOf(("me" to it) to 0, (it to "me") to 0) }.toMap()
        return SittingPreferences(preferencesMap + relationsToMe)
    }

    private fun parsePreferenceLine(s: String): Pair<Pair<String, String>, Int> {
        val splitLine = s.split(" ")
        val name1 = splitLine.first()
        val name2 = splitLine.last().removeSuffix(".")
        val looseOrGain = when (splitLine[2]) {
            "lose" -> -1
            "gain" -> 1
            else -> throw IllegalArgumentException("Input line not matching expectations")
        }
        val value = looseOrGain * splitLine[3].toInt()

        return (name1 to name2) to value
    }
}

class SittingPlan {
    //that gets factorial and will be very slow with higher diners number
    fun findBestSitting(prefs: SittingPreferences): Pair<MutableList<String>, Int>? {
        val sittingPossibilities = Collections2.permutations(prefs.getAllNames())
        return sittingPossibilities.map { it to calculateHappiness(it, prefs) }
            .maxBy { (list, happiness) -> happiness }
    }

    internal fun calculateHappiness(sittingLayout: List<String>, prefs: SittingPreferences): Int {
        return zipTwoWayInCircle(sittingLayout)
            .mapNotNull { prefs.getPreference(it) }
            .sum()
    }

    private fun <T> zipTwoWayInCircle(l: List<T>): List<Pair<T, T>> {
        return l.zipWithNext() + (l.last() to l.first()) + l.reversed().zipWithNext() + (l.first() to l.last())
    }
}
