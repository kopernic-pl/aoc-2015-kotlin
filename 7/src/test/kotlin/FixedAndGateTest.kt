import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class FixedAndGateTest {
    @Test
    fun `should apply and function`() {
        val a = 255
        val b = 15

        assertEquals(15, FixedAndGate(a, listOf(), "").apply(b))
    }

    @Test
    fun `should provide template and match input text`() {
        val a = "ab AND ad -> ae"

        assertFalse { FixedAndGate.template.toRegex() matches a }
    }

    @Test
    fun `should create object from string`() {
        val a = "42 AND ad -> ae"

        val element = FixedAndGate.parse(a)
        assertTrue { element != null }
        assertTrue { element is FixedAndGate }
        val gate = element as FixedAndGate
        assertTrue {gate.inputs == listOf("ad")}
        assertTrue {gate.output == "ae"}
    }

    @Test
    fun `should provide template and match input text with number as first argument`() {
        val a = "42 AND ad -> ae"

        assertTrue { FixedAndGate.template.toRegex() matches a }
    }
}