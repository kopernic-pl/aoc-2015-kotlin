import kotlin.test.Test
import kotlin.test.assertEquals

internal class InputReaderTest {

    @Test
    fun `should read input line`() {
        val from = "Norrath"
        val to = "Straylight"
        val distance = 9
        val input = "$from to $to = $distance"

        val out = InputReader.parse(input)
        assertEquals(2, out.size)
        assertEquals(out, listOf((from to to) to distance, (to to from) to distance))
    }
}
