import com.google.common.io.Resources

const val TIMER_INPUT = 2503

@Suppress("UnstableApiUsage")
fun main() {
    val reindeers = Resources.getResource("input.txt")
        .readText().lineSequence()
        .map { ReindeerReader.read(it) }

    val winningReindeerBySpeed = reindeers
        .map { (name, r) -> name to r.getPosition(TIMER_INPUT) }
        .sortedByDescending { (_, distance) -> distance }
        .first()

    println("Winner by speed: $winningReindeerBySpeed")

    val leadershipResult = ReindeerLeadershipRaceSimulator(TIMER_INPUT).calculateLeadershipRace(reindeers)

    val winnerByLeadership = leadershipResult.toList().maxByOrNull { (_, result) -> result }

    println("Winner by leadership: $winnerByLeadership")
}

class ReindeerLeadershipRaceSimulator(private val raceTime: Int) {
    fun calculateLeadershipRace(reindeers: Sequence<Pair<String, Reindeer>>): Map<String, Int> {
        return (1..raceTime).asSequence()
            .fold(reindeers.toMap().keys.map { name -> name to 0 }.toMap()) { result, second ->
                val reindeerResults = reindeers.map { (name, r) -> name to r.getPosition(second) }
                val maxDistance = reindeerResults.sortedByDescending { (_, distance) -> distance }
                    .first().second

                val leaders = reindeerResults
                    .filter { (_, distance) -> distance == maxDistance }
                    .map { (name, _) -> name }

                result.mapValues { (name, score) ->
                    when {
                        leaders.contains(name) -> score + 1
                        else -> score
                    }
                }
            }
    }
}

object ReindeerReader {
    fun read(description: String): Pair<String, Reindeer> {
        description.split(" ").let {
            return it[nameIdx] to Reindeer(it[speedIdx].toInt(), it[flyTime].toInt(), it[restTime].toInt())
        }
    }

    private const val nameIdx = 0
    private const val speedIdx = 3
    private const val flyTime = 6
    private const val restTime = 13
}

class Reindeer(private val speed: Int, private val speedTime: Int, private val sleep: Int) {

    val cycleTime: Int
        get() = speedTime + sleep

    internal fun getPositionInLastCycle(timeInLastCycle: Int): Int {
        return when (timeInLastCycle) {
            in 0..speedTime -> speed * timeInLastCycle
            in speedTime + 1..cycleTime -> getPositionInLastCycle(speedTime)
            else -> throw IllegalArgumentException()
        }
    }

    internal fun fullCycles(time: Int): Int = time / cycleTime

    internal fun reminderTime(time: Int): Int = time % cycleTime

    fun getPosition(time: Int): Int {
        return fullCycles(time) * speed * speedTime + getPositionInLastCycle(reminderTime(time))
    }
}
