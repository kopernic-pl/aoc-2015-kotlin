import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class LookAndSayKtTest {
    @Test
    fun `empty string splits to empty list`() {
        assertTrue { "".splitOnChange().isEmpty() }
    }

    @Test
    fun `one char string splits to this char`() {
        val oneCharString = "a"
        val split = oneCharString.splitOnChange()
        assertEquals(1, split.size)
        assertEquals(oneCharString, split[0])
    }

    @Test
    fun `one char multiplied splits to one group`() {
        val oneCharString = "aaaaaaaa"
        val split = oneCharString.splitOnChange()
        assertEquals(1, split.size)
        assertEquals(oneCharString, split[0])
    }

    @Test
    fun `two char multiplied splits to two groups`() {
        val part1 = "aaaaaaaa"
        val part2 = "bbb"
        val string = part1 + part2
        val split = string.splitOnChange()
        assertEquals(2, split.size)
        assertEquals(part1, split[0])
        assertEquals(part2, split[1])
    }

    @Test
    fun `should split alternating letters string`() {
        val repeats = 5
        val string = "ab".repeat(repeats)
        val split = string.splitOnChange()
        assertEquals(repeats * 2, split.size)
    }
}
