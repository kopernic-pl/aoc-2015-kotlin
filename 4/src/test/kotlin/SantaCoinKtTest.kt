import SantaCoin.startsWithZeros
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertEquals

class SantaCoinKtTest {
    @Test
    fun `should calc MD 5 of regular string`() {
        val sampleString = "abcdef609043"
        assertEquals(
            "000001dbbfa3a5c83a2d506429c7b00e",
            SantaCoin.hashString(sampleString).toString()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "0, true",
        "1, true",
        "2, true",
        "3, true",
        "4, true",
        "5, true",
        "6, false"
    )
    fun `should check if hash hex representation starts with zeros`(input: Int, expected: Boolean) {
        val sampleHash = SantaCoin.hashString("abcdef609043")
        assertEquals(
            expected,
            sampleHash.startsWithZeros(input)
        )
    }

    @Test
    fun `should find`() {
        val sampleString = "abcdef"
        assertEquals(
            609043,
            SantaCoin.findNumberHashWithTrailingZeros(sampleString, 5).first
        )
    }
}
