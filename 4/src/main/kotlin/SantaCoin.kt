import com.google.common.hash.HashCode
import com.google.common.hash.Hashing
import java.nio.charset.Charset.defaultCharset
import java.util.stream.IntStream

@Suppress("UnstableApiUsage")
object SantaCoin {
    @Suppress("DEPRECATION")
    private val md5hash = Hashing.md5()

    internal fun hashString(s: String) = md5hash.hashString(s, defaultCharset())!!

    internal fun HashCode.startsWithZeros(countOfLeadingZeros: Int): Boolean =
        this.toString().startsWith("0".repeat(countOfLeadingZeros))

    internal fun findNumberHashWithTrailingZeros(input: String, trailingZeros: Int): Pair<Int, HashCode> {
        return IntStream.rangeClosed(0, Int.MAX_VALUE)
            .mapToObj { Pair(it, input + it) }
            .map { (i, valToHash) -> Pair(i, hashString(valToHash)) }
            .filter { (_, hash) -> hash.startsWithZeros(trailingZeros) }
            .findFirst()
            .orElseThrow { RuntimeException("Hash could not be found in range from zero to MAX INT") }
    }

    fun aoc4a() {
        val input = "yzbqklnj"

        println(
            "Number that hashes with five trailing zeros: " +
                findNumberHashWithTrailingZeros(input, trailingZeros = 5)
        )
    }

    fun aoc4b() {
        val input = "yzbqklnj"

        println(
            "Number that hashes with six trailing zeros: " +
                    findNumberHashWithTrailingZeros(input, trailingZeros = 6)
        )
    }
}

fun main(args: Array<String>) {
    SantaCoin.aoc4a()
    SantaCoin.aoc4b()
}
