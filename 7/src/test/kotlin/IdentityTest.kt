import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class IdentityTest {

    @Test
    fun `should pass the value`() {

        val value = Random.nextInt()

        assertEquals(value, Identity(listOf(), "").apply(value))
    }

    @Test
    fun `should provide template and match input text`() {
        val a = "jp -> js"

        assertTrue { Identity.template.toRegex() matches a }
    }

    @Test
    fun `should provide template and match input text with number`() {
        val a = "1410 -> js"

        assertFalse { Identity.template.toRegex() matches a }
    }
}
