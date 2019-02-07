import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class MustContainThreeLettersIncreaseTest {

    @Test
    fun validate() {
        val s = "aksjdklasjabcaklsjdlasjd"
        assertTrue { MustContainThreeLettersIncrease().validate(s) }
    }
}
