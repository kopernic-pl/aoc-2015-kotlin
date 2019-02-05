import com.google.common.collect.Collections2
import com.google.common.io.Resources
import java.io.File

typealias Place = String
typealias Leg = Pair<Place, Place>
typealias LegWithDistance = Pair<Leg, Int>

fun main() {
    calcDistances()
}

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

fun routeToLegs(route: List<Place>): List<Leg> = route.zip(route.drop(1))
fun distinctPlacesFromRoutes(routes: Set<Leg>) = routes.flatMap { it.toList() }.toSet()

object InputReader {
    fun parse(s: String): List<LegWithDistance> {
        val splits = s.split(' ')
        return listOf((splits[0] to splits[2]) to splits[4].toInt(), (splits[2] to splits[0]) to splits[4].toInt())
    }
}
