import org.amshove.kluent.`should equal`
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldHaveSize
import kotlin.test.Test

internal class EggNogContainersKtTest {

    @Test
    fun `should generate no combination when no elements`() {
        generateCombinationsOfIndices(0).shouldBeEmpty()
    }

    @Test
    fun `should generate one combination when one element`() {
        generateCombinationsOfIndices(1) `should equal` listOf(setOf(0))
    }

    @Test
    fun `should generate three combinations when two elements`() {
        generateCombinationsOfIndices(2)
            .shouldHaveSize(3)
            .shouldEqual(listOf(setOf(0), setOf(1), setOf(0, 1)))
    }
}
