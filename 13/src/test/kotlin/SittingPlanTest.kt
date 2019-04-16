import org.amshove.kluent.`should be equal to`
import kotlin.test.Test

internal class SittingPlanTest {

    @Test
    fun shouldCalculateHappinessForSitting_whenEveryoneIsHappy(){
        val planner = SittingPlan()

        val sittingLayout = listOf("a","b","c")
        val preferencesString = """
            a would gain 1 happiness units by sitting next to b.
            a would gain 1 happiness units by sitting next to c.
            b would gain 1 happiness units by sitting next to c.
            b would gain 1 happiness units by sitting next to a.
            c would gain 1 happiness units by sitting next to a.
            c would gain 1 happiness units by sitting next to b.
        """.trimIndent()
        val prefs = SittingPreferences(preferencesString)
        planner.calculateHappiness(sittingLayout, prefs) `should be equal to` 6
    }

    @Test
    fun shouldCalculateHappinessForSitting_whenThereAreSomeProblems(){
        val planner = SittingPlan()

        val sittingLayout = listOf("a","b","c")
        val preferencesString = """
            a would gain 1 happiness units by sitting next to b.
            a would lose 1 happiness units by sitting next to c.
            b would gain 1 happiness units by sitting next to c.
            b would lose 1 happiness units by sitting next to a.
            c would gain 1 happiness units by sitting next to a.
            c would lose 1 happiness units by sitting next to b.
        """.trimIndent()
        val prefs = SittingPreferences(preferencesString)
        planner.calculateHappiness(sittingLayout, prefs) `should be equal to` 0
    }
}