import com.google.common.collect.Collections2
import com.google.common.io.Resources
import java.io.File

typealias Place = String
typealias Leg = Pair<Place, Place>
typealias LegWithDistance = Pair<Leg, Int>

fun main() {
    DistanceCalculator().calcDistances()
}

class DistanceCalculator {
    @Suppress("UnstableApiUsage")
    fun calcDistances() {
        val input = File(Resources.getResource("input.txt").toURI()).readLines()

        val distances = input.flatMap(InputReader::parse).toMap()

        val allPlaces = distinctPlacesFromRoutes(distances.keys)
        val allRoutes = Collections2.permutations(allPlaces)

        val listOfAllRoutesByLegs = allRoutes.map(::routeToLegs)
        val listOfAllDistances = listOfAllRoutesByLegs.map { legs ->
            legs
                .mapNotNull { distances[it] }
                .sum()
        }

        println("Min: ${listOfAllDistances.min()}")
        println("Max: ${listOfAllDistances.max()}")
    }

    internal fun routeToLegs(route: List<Place>): List<Leg> = route.zipWithNext()
    private fun distinctPlacesFromRoutes(routes: Set<Leg>) = routes.flatMap { it.toList() }.toSet()
}

internal object InputReader {
    private const val firstPlaceIdx = 0
    private const val secondPlaceIdx = 2
    private const val distanceIdx = 4
    fun parse(s: String): List<LegWithDistance> {
        val splits = s.split(' ')
        return listOf(
            (splits[firstPlaceIdx] to splits[secondPlaceIdx]) to splits[distanceIdx].toInt(),
            (splits[secondPlaceIdx] to splits[firstPlaceIdx]) to splits[distanceIdx].toInt()
        )
    }
}
