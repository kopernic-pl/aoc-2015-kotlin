import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class OrGateTest {
    @Test
    fun `should apply or function`() {
        val a = 10
        val b = 20

        assertEquals(30, OrGate(listOf(), "").apply(a, b))
    }

    @Test
    fun `should provide template and match input text`() {
        val a = "ab OR ad -> ae"

        assertTrue { OrGate.template.toRegex() matches a }
    }
}