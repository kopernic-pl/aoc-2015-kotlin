import com.google.common.io.Resources
import java.io.File

@Suppress("UnstableApiUsage")
object NaughtyNice {

    fun aoc5a() {
        val x = File(Resources.getResource("input.txt").toURI()).readLines().map {
            when {
                it.containsAnyLetterTwiceInRow() && !it.containsForbidden() && it.countVovels() >= 3 -> true
                else -> false
            }
        }.count { it == true }

        println(x)
    }

    fun aoc5b() {
        val x = File(Resources.getResource("input.txt").toURI()).readLines().map {
            when {
                it.containsLetterAnythingLetterPattern() && it.hasTwoNonOverlappingTwin() -> true
                else -> false
            }
        }.count { it == true }

        println(x)
    }

    fun String.countVovels(): Int {
        return this.map {
            when (it) {
                'a', 'e', 'i', 'o', 'u' -> 1
                else -> 0
            }
        }
            .sum()
    }

    fun String.containsForbidden(): Boolean {
        return this.contains("ab") || this.contains("cd") || this.contains("pq") || this.contains("xy")
    }

    fun String.containsAnyLetterTwiceInRow(): Boolean {
        return this.matches(Regex(".*(\\w)\\1+.*"))
    }

    fun String.containsLetterAnythingLetterPattern():Boolean {
        return this.matches(Regex(".*(\\w).\\1.*"))
    }

    fun String.hasTwoNonOverlappingTwin():Boolean {
        return this.matches(Regex(".*(\\w\\w).*\\1.*"))
    }
}

fun main(args: Array<String>) {
    NaughtyNice.aoc5a()
    NaughtyNice.aoc5b()
}
