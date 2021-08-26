import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class SantaPassKtTest {

    @Test
    fun `should increment pass`() {
        assertEquals("abcdffaa", SantaPass().generateNextPass("abcdefgh"))
    }

    @Test
    fun `should increment another pass`() {
        assertEquals("ghjaabcc", SantaPass().generateNextPass("ghijklmn"))
    }

    @Test
    fun `should increment pass multiple times`() {
        assertEquals(listOf("abcdffaa", "abcdffbb", "abcdffcc"), SantaPass().generateNextPasswords("abcdefgh", 3))
    }

    @Test
    fun `should fail on empty string`() {
        var y = ""
        assertThrows<IllegalStateException> { y++ }
    }

    @ParameterizedTest
    @ValueSource(chars = ['A', 'G', 'J', 'L', 'N', 'P', 'T', 'U', 'V', 'W', 'Z'])
    fun `should be illegal to increment chars outside a-z range`(char: Char) {
        assertThrows<IllegalStateException> { char.incrementWithCarry() }
    }

    @ParameterizedTest
    @ValueSource(chars = ['@', '!', '$', '%', '^', '&', '*', '+', '\\', '<', '.'])
    fun `should be illegal to increment non-lower-case letters`(char: Char) {
        assertThrows<IllegalStateException> { char.incrementWithCarry() }
    }

    @ParameterizedTest
    @ValueSource(chars = ['a', 'g', 'j', 'l', 'n', 'p', 't', 'u', 'v', 'w'])
    fun `should increment non-z letter`(inputChar: Char) {
        val (carry, incrementedChar) = inputChar.incrementWithCarry()
        assertFalse { incrementedChar == inputChar }
        assertEquals(inputChar.inc(), incrementedChar)
        assertFalse { carry }
    }

    @Test
    fun `should increment z letter`() {
        val (carry, incrementedChar) = 'z'.incrementWithCarry()
        assertEquals('a', incrementedChar)
        assertTrue { carry }
    }

    @Test
    fun `should increment a string`() {
        assertEquals("b", "a".inc())
    }

    @Test
    fun `should increment a bit longer string`() {
        assertEquals("ab", "aa".inc())
    }

    @Test
    fun `should increment a bit bit longer string`() {
        assertEquals("abb", "aba".inc())
    }

    @Test
    fun `should increment string with a single carry`() {
        assertEquals("ba", "az".inc())
    }

    @Test
    fun `should increment longer string with a single carry`() {
        assertEquals("aaaaba", "aaaaaz".inc())
    }

    @Test
    fun `should increment longer string with a multi carry`() {
        assertEquals("aaabaa", "aaaazz".inc())
    }

    @Test
    fun `should make the return longer if frst letter with carry`() {
        assertEquals("aa", "z".inc())
    }

    @Test
    fun `should increment lots of zees`() {
        assertEquals("aaaaaaa", "zzzzzz".inc())
    }
}
