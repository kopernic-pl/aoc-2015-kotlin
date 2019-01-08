import NaughtyNice.containsAnyLetterTwiceInRow
import NaughtyNice.countVovels
import NaughtyNice.containsForbidden
import NaughtyNice.containsLetterAnythingLetterPattern
import NaughtyNice.hasTwoNonOverlappingTwin
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

//import kotlin.test.assertEquals
//import kotlin.test.assertTrue
//import kotlin.test.assertFalse

class NaughtyNiceKtTest {

    @Test
    fun `should count aeiou vovels`() {
        assertEquals(3, "aei".countVovels())
        assertEquals(3, "xazegov".countVovels())
        assertEquals(15, "aeiouaeiouaeiou".countVovels())
    }

    @ParameterizedTest
    @CsvSource(
        "abcd, false",
        "xx, true",
        "abcdde, true",
        "aabbccdd, true"
    )
    fun `should detect letters twice in a row`(s: String, expected: Boolean) {
        assertEquals(expected, s.containsAnyLetterTwiceInRow())
    }

    @Test
    fun `should not contain forbidden words`() {
        assertTrue { "haegwjzuvuyypxyu".containsForbidden()}
        assertTrue { "ab".containsForbidden()}
        assertTrue { "cd".containsForbidden()}
        assertTrue { "pq".containsForbidden()}
        assertTrue { "xy".containsForbidden()}
        assertTrue { "abc".containsForbidden()}
        assertFalse { "def".containsForbidden()}
    }

    @Test
    fun `should match letter-anything-letter`() {
        assertTrue { "xyx".containsLetterAnythingLetterPattern()}
        assertTrue { "abcdefeghi".containsLetterAnythingLetterPattern()}
        assertTrue { "aaa".containsLetterAnythingLetterPattern()}
        assertFalse { "abc".containsLetterAnythingLetterPattern()}
    }

    @Test
    fun `should match pair-whatever-pair`() {
        assertTrue { "xyxy".hasTwoNonOverlappingTwin()}
        assertTrue { "aabcdefgaa".hasTwoNonOverlappingTwin()}
        assertFalse { "aaa".hasTwoNonOverlappingTwin()}
    }
}
