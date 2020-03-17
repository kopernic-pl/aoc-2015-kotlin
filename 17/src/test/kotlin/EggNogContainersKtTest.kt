import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import kotlin.test.Test

internal class EggNogContainersKtTest {

    @Test
    fun `should generate no combination when no elements`() {
        generateCombinationsOfIndices(0).shouldBeEmpty()
    }

    @Test
    fun `should generate one combination when one element`() {
        generateCombinationsOfIndices(1) `should be equal to` listOf(setOf(0))
    }

    @Test
    fun `should generate three combinations when two elements`() {
        generateCombinationsOfIndices(2)
            .shouldHaveSize(3)
            .shouldBeEqualTo(listOf(setOf(0), setOf(1), setOf(0, 1)))
    }
}
