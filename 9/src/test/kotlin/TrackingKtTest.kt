import org.amshove.kluent.`should be equal to`
import kotlin.test.Test

internal class TrackingKtTest {

    @Test
    fun `should expand list to list of pairs`() {
        val listOfPairs = DistanceCalculator().routeToLegs(listOf("a", "b", "c"))
        listOfPairs `should be equal to` listOf("a" to "b", "b" to "c")
    }
}
