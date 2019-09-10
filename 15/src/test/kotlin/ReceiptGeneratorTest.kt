import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should contain`
import kotlin.test.Test

internal class ReceiptGeneratorTest {
    @Test
    fun `should generate all possible recipes`() {
        val result = ReceiptGenerator.generate(9, 4)
        result.size `should be equal to` 220
        result.forEach {
            it.size `should be equal to` 4
            it.sum() `should be equal to` 9
        }
        result `should contain` listOf(0, 0, 0, 9)
        result `should contain` listOf(9, 0, 0, 0)
        result `should contain` listOf(1, 2, 3, 3)
    }
}
