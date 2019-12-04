import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

internal class TrackingKtTest {

    @Test
    fun `should expand list to list of pairs`(){
        val listOfPairs = DistanceCalculator().routeToLegs(listOf("a", "b", "c"))
        assertTrue{ listOfPairs == listOf("a" to "b", "b" to "c")}
    }
}
