import org.amshove.kluent.shouldHaveSize
import kotlin.test.Test


internal class AuntGuesserKtTest {
    private val testInput = listOf(
        "Sue 177: pomeranians: 4, vizslas: 7, trees: 3",
        "Sue 178: vizslas: 6, perfumes: 10, akitas: 6",
        "Sue 179: cars: 4, akitas: 4, trees: 4",
        "Sue 180: akitas: 8, goldfish: 6, trees: 9"
    )

    @Test
    fun `should match all aunts if no checkers`() {
        val checkers: Map<String, (Int) -> Boolean> = mapOf()
        findAunt(testInput, checkers) { it shouldHaveSize testInput.size }
    }

    @Test
    fun `should match all but not goldfish`() {
        val checkers = mapOf("goldfish" to isEqualTo(7))
        findAunt(testInput, checkers) { it shouldHaveSize testInput.size - 1 }
    }

    @Test
    fun `should match exactly one aunt`() {
        val checkers = mapOf(
            "goldfish" to isEqualTo(6),
            "trees" to isEqualTo(9),
            "akitas" to isEqualTo(8)
        )
        findAunt(testInput, checkers) { it shouldHaveSize 1 }
    }
}
