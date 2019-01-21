import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class RightShifterTest {
    private val oneRightShifter = RightShifter(1, listOf(), "")

    @Test
    fun `should bit shift one-hot number`() {
        val a = 32

        assertEquals(16, oneRightShifter.apply(a))
    }

    @Test
    fun `should bit shift multi-hot`() {
        val a = 255

        assertEquals(127, oneRightShifter.apply(a))
    }

    @Test
    fun `should bit shift other`() {
        val a = 5

        assertEquals(2, oneRightShifter.apply(a))
    }

    @Test
    fun `should provide template and match input text`() {
        val a = "jp RSHIFT 5 -> js"

        assertTrue { RightShifter.template.toRegex() matches a }
    }
}
