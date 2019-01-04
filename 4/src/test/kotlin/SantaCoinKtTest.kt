import SantaCoin.startsWithZeros
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class SantaCoinKtTest {
    @Test
    fun `should calc MD 5 of regular string`() {
        val sampleString = "abcdef609043"
        assertEquals(
            "000001dbbfa3a5c83a2d506429c7b00e",
            SantaCoin.hashString(sampleString).toString()
        )
    }

    @Test
    fun `should check if hash hex representation starts with zeros`() {
        val sampleHash = SantaCoin.hashString("abcdef609043")
        assertTrue {sampleHash.startsWithZeros(0) }
        assertTrue {sampleHash.startsWithZeros(1) }
        assertTrue {sampleHash.startsWithZeros(2) }
        assertTrue {sampleHash.startsWithZeros(3) }
        assertTrue {sampleHash.startsWithZeros(4) }
        assertTrue {sampleHash.startsWithZeros(5) }
        assertFalse {sampleHash.startsWithZeros(6) }
    }

    @Test
    fun `should find `() {
        val sampleString = "abcdef"
        assertEquals(
            609043,
            SantaCoin.findNumberHashWithTrailingZeros(sampleString, 5).first
        )
    }
}
