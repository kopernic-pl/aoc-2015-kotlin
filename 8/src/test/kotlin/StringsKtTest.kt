import kotlin.test.Test
import kotlin.test.assertEquals

internal class StringsKtTest {

    @Test
    fun `should strip trailing and leading quote`() {
        assertEquals("a", "\"a\"".transformToMem())
    }

    @Test
    fun `should replace escaped quote`() {
        assertEquals("a\"a", "a\\\"a".transformToMem())
    }

    @Test
    fun `should replace escaped backslash`() {
        assertEquals("a\\", "a\\\\".transformToMem())
    }

    @Test
    fun `should replace ascii code with letter R`() {
        assertEquals("aR", "a\\xff".transformToMem())
    }

    @Test
    fun `should escape quotes and quote`() {
        assertEquals("\"\\\"\\\"\"", "\"\"".escape())
    }

    @Test
    fun `should quote`() {
        assertEquals("\"\\\"abc\\\"\"", "\"abc\"".escape())
    }

    @Test
    fun `should quote and escape slashes and quotes`() {
        assertEquals("\"\\\"aaa\\\\\\\"aaa\\\"\"", "\"aaa\\\"aaa\"".escape())
    }
}
