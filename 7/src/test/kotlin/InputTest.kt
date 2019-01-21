import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class InputTest {

    @Test
    fun `should pass the value`() {

        val value = Random.nextInt()

        assertEquals(value, InputElement(value, "", listOf()).apply())
    }

    @Test
    fun `should provide template and match input text`() {
        val a = "jp -> js"

        assertFalse { InputElement.template.toRegex() matches a }
    }

    @Test
    fun `should provide template and match input text with number`() {
        val a = "1410 -> js"

        assertTrue { InputElement.template.toRegex() matches a }
    }
}