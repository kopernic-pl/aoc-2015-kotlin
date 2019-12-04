import com.google.common.io.Resources
import java.io.File

@Suppress("UnstableApiUsage")
object NaughtyNice {
    private const val minimumVowels = 3
    fun aoc5a() {

        val x = File(Resources.getResource("input.txt").toURI()).readLines().map {
            when {
                it.containsAnyLetterTwiceInRow() && !it.containsForbidden() && it.countVovels() >= minimumVowels -> true
                else -> false
            }
        }.count { it }

        println(x)
    }

    fun aoc5b() {
        val x = File(Resources.getResource("input.txt").toURI()).readLines().map {
            when {
                it.containsLetterAnythingLetterPattern() && it.hasTwoNonOverlappingTwin() -> true
                else -> false
            }
        }.count { it }

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

    fun String.containsLetterAnythingLetterPattern(): Boolean {
        return this.matches(Regex(".*(\\w).\\1.*"))
    }

    fun String.hasTwoNonOverlappingTwin(): Boolean {
        return this.matches(Regex(".*(\\w\\w).*\\1.*"))
    }
}

fun main() {
    NaughtyNice.aoc5a()
    NaughtyNice.aoc5b()
}
