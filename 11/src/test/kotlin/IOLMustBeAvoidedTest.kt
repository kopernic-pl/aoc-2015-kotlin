import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

internal class IOLMustBeAvoidedTest {

    @Test
    fun `should validate string`() {
        val s = "aksjdkasjabcaksjdasjd"
        assertTrue { IOLMustBeAvoided().validate(s) }
    }

    @Test
    fun `should fail on i`() {
        val s = "i"
        assertFalse { IOLMustBeAvoided().validate(s) }
    }

    @Test
    fun `should fail on o`() {
        val s = "o"
        assertFalse { IOLMustBeAvoided().validate(s) }
    }

    @Test
    fun `should fail on l`() {
        val s = "l"
        assertFalse { IOLMustBeAvoided().validate(s) }
    }
}
