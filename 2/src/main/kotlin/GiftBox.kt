import com.google.common.io.Resources
import java.io.File

class GiftBox private constructor(
    private val x: Int,
    private val y: Int,
    private val z: Int
) {

    val wrappingArea: Int
        get() = this.calcWrappingArea()
    val ribbonNeeded: Int
        get() = this.getVolume() + this.getShortestPerimeter()

    companion object Factory {
        fun create(): GiftBox = create(0, 0, 0)

        fun create(x: Int, y: Int, z: Int) = GiftBox(x, y, z)

        @Throws(NumberFormatException::class)
        fun create(s: String): GiftBox {
            val edges = s.splitToSequence('x', limit = 3).map { it.toInt() }.toList()
            return create(edges[0], edges[1], edges[2])
        }
    }

    private fun calcWrappingArea(): Int {
        val a1 = x * y
        val a2 = y * z
        val a3 = x * z
        val slack = (intArrayOf(a1, a2, a3).min()
            ?: throw RuntimeException("Class not properly initialized"))
        return 2 * a1 + 2 * a2 + 2 * a3 + slack
    }

    private fun getVolume() = x * y * z

    private fun getShortestPerimeter() =
        intArrayOf(2 * x + 2 * y, 2 * x + 2 * z, 2 * y + 2 * z).min() ?: throw RuntimeException()
}

fun aoc2a() {
    println(
        "Total paper needed: ${File(Resources.getResource("input2").file)
            .reader().use { reader ->
                reader.readLines()
                    .map { GiftBox.create(it) }
                    .map { it.wrappingArea }
                    .sum()
            }}"
    )
}

fun aoc2b() {
    println(
        "Total ribbon needed: ${File(Resources.getResource("input2").file)
            .reader().use { reader ->
                reader.readLines()
                    .map { GiftBox.create(it) }
                    .map { it.ribbonNeeded }
                    .sum()
            }}"
    )
}

fun main(args: Array<String>) {
    aoc2a()
    aoc2b()
}