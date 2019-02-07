import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

internal class MustContainTwoPairsTest {

    @Test
    fun validateString() {
        assertTrue { MustContainTwoPairs().validate("aabb") }
    }

    @Test
    fun `should fail on three letters`() {
        assertFalse { MustContainTwoPairs().validate("aaab") }
    }

    @Test
    fun `should fail on four or more letters`() {
        assertTrue { MustContainTwoPairs().validate("aaaa") }
    }

    @Test
    fun `should match more letters`() {
        assertTrue { MustContainTwoPairs().validate("asdfaamlodpp") }
    }
}
