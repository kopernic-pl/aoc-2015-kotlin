import org.amshove.kluent.`should be equal to`
import kotlin.test.Test

internal class AccountingTest {

    @Test
    fun `should add all numbers found`() {
        val testString = """
            [{
                "a":1,
                "b":2,
                "c":3
            },
            [1,2,3]]
        """

        Accounting(testString).addAllNumbers() `should be equal to` 12
    }
}
