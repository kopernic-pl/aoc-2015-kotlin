import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class LeftShifterTest {
    private val oneLeftShifter = LeftShifter(1, listOf(), "")

    @Test
    fun `should bit shift one-hot number`() {
        val a = 32

        assertEquals(64, oneLeftShifter.apply(a))
    }

    @Test
    fun `should bit shift multi-hot`() {
        val a = 255

        assertEquals(510, oneLeftShifter.apply(a))
    }

    @Test
    fun `should bit shift other`() {
        val a = 5

        assertEquals(10, oneLeftShifter.apply(a))
    }

    @Test
    fun `should provide template and match input text`() {
        val a = "jp LSHIFT 5 -> js"

        assertTrue { LeftShifter.template.toRegex() matches a }
    }
}
