import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class NegatorTest {
    private val negator = Negator(listOf(), "")

    @Test
    fun `should negate one-hot number`() {
        val a = 32

        assertEquals(-33, negator.apply(a))
    }

    @Test
    fun `should negate multi-hot`() {
        val a = 255

        assertEquals(-256, negator.apply(a))
    }

    @Test
    fun `should negate other`() {
        val a = 5

        assertEquals(-6, negator.apply(a))
    }

    @Test
    fun `should provide template and match input text`() {
        val a = "NOT a -> js"

        assertTrue { Negator.template.toRegex() matches a }
    }
}
