import org.amshove.kluent.invoking
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should contain all`
import org.amshove.kluent.`should throw`
import org.amshove.kluent.shouldBeNull
import kotlin.test.Test

internal class SittingPreferencesTest {
    private val input = """
            David would gain 9 happiness units by sitting next to Mallory.
            Eric would lose 51 happiness units by sitting next to Alice.
    """.trimIndent()

    @Test
    fun shouldParseInput_withTwoPreferences() {
        val prefs = SittingPreferences(input)
        prefs.preferencesMap.size `should be equal to` 2
        prefs.getPreference("David", "Mallory")!! `should be equal to` 9
        prefs.getPreference("Eric", "Alice")!! `should be equal to` -51
    }

    @Test
    fun shouldNotCreateReverseRelations() {
        val prefs = SittingPreferences(input)
        prefs.getPreference("David", "Mallory")!! `should be equal to` 9
        prefs.getPreference("Mallory", "David").shouldBeNull()
    }

    @Test
    fun shouldFail_whenMisformatted() {
        invoking {
            SittingPreferences("David would gain infinite happiness units by sitting next to Mallory.")
        } `should throw` IllegalArgumentException::class
    }

    @Test
    fun `should get all unique names`() {
        val allNames = SittingPreferences(input).getAllNames()
        allNames.size `should be equal to` 4
        allNames `should contain all` setOf("David", "Mallory", "Eric", "Alice")
    }
}
