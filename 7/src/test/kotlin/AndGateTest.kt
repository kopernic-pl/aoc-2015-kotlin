import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class AndGateTest {
    @Test
    fun `should apply and function`() {
        val a = 255
        val b = 15

        assertEquals(15, AndGate(listOf(), "").apply(a, b))
    }

    @Test
    fun `should provide template and match input text`() {
        val a = "ab AND ad -> ae"

        assertTrue { AndGate.template.toRegex() matches a }
    }

    @Test
    fun `should not match input text when gate is fixed`() {
        val a = "2 AND ad -> ae"

        assertFalse { AndGate.template.toRegex() matches a }
    }

    @Test
    fun `should create object from string`() {
        val a = "ab AND ad -> ae"

        val element = AndGate.parse(a)
        assertTrue { element != null }
        assertTrue { element is AndGate }
        val gate = element as AndGate
        assertTrue { gate.inputs == listOf("ab", "ad") }
        assertTrue { gate.output == "ae" }
    }

    @Test
    fun `should provide template and match input text with number as first argument`() {
        val a = "42 AND ad -> ae"

        assertFalse { AndGate.template.toRegex() matches a }
    }
}
