import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should contain all`
import org.amshove.kluent.`should not contain`
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldHaveSize
import kotlin.test.Test

internal class FieldTest {
    @Test
    fun shouldConstruct() {
        Field(2).size `should be equal to` 2
    }

    @Test
    fun shouldBeSquare() {
        val size = 2
        Field(size).numberOfLights() `should be equal to` size * size
    }

    @Test
    fun shouldInitOneLight() {
        val f = Field(1)
        f.size `should be equal to` 1
        f.numberOfLights() `should be equal to` 1
        f.numberOfOnLights() `should be equal to` 0
        val onlyField = f.fieldByLocation(0, 0)
        onlyField.state `should be` false
        onlyField.neighbours.shouldBeEmpty()
    }

    @Test
    fun shouldInitFourLights() {
        val f = Field(2)
        f.size `should be equal to` 2
        f.numberOfLights() `should be equal to` 4
        f.numberOfOnLights() `should be equal to` 0

        f.fieldByLocation(0, 0).neighbours `should contain all` listOf(
            f.fieldByLocation(0, 1),
            f.fieldByLocation(1, 0),
            f.fieldByLocation(1, 1)
        ) `should not contain` f.fieldByLocation(0, 0)

        f.fieldByLocation(0, 1).neighbours `should contain all` listOf(
            f.fieldByLocation(0, 0),
            f.fieldByLocation(1, 0),
            f.fieldByLocation(1, 1)
        ) `should not contain` f.fieldByLocation(0, 1)

        f.fieldByLocation(1, 0).neighbours `should contain all` listOf(
            f.fieldByLocation(0, 0),
            f.fieldByLocation(0, 1),
            f.fieldByLocation(1, 1)
        ) `should not contain` f.fieldByLocation(1, 0)

        f.fieldByLocation(1, 1).neighbours `should contain all` listOf(
            f.fieldByLocation(0, 0),
            f.fieldByLocation(1, 0),
            f.fieldByLocation(0, 1)
        ) `should not contain` f.fieldByLocation(1, 1)
    }

    @Test
    fun shouldCalcProperNeighboursFor9lights() {
        val f = Field(3)
        f.size `should be equal to` 3
        f.numberOfLights() `should be equal to` 3 * 3

        f.fieldByLocation(1, 1).neighbours.shouldHaveSize(8) `should contain all` listOf(
            f.fieldByLocation(0, 0),
            f.fieldByLocation(0, 1),
            f.fieldByLocation(0, 2),
            f.fieldByLocation(1, 0),
            f.fieldByLocation(1, 2),
            f.fieldByLocation(2, 0),
            f.fieldByLocation(2, 1),
            f.fieldByLocation(2, 2)
        ) `should not contain` f.fieldByLocation(1, 1)
    }

    @Test
    fun shouldInitFourLightsButOneStuckOn() {
        val f = Field(2, listOf(1 to 1))
        f.size `should be equal to` 2
        f.numberOfLights() `should be equal to` 4
        f.numberOfOnLights() `should be equal to` 1
    }

    @Test
    fun lightWithNoNeighboursShouldTurnOff() {
        val f = Field(2) { i, j ->
            when {
                i == 1 && j == 1 -> true
                else -> false
            }
        }
        f.size `should be equal to` 2
        f.numberOfLights() `should be equal to` 4
        f.numberOfOnLights() `should be equal to` 1

        f.calcNextState()
        f.switch()
        f.numberOfOnLights() `should be equal to` 0
    }

    @Test
    fun stuckLightWithNoNeighboursShouldNotChange() {
        val f = Field(2, listOf(1 to 1))
        f.size `should be equal to` 2
        f.numberOfLights() `should be equal to` 4
        f.numberOfOnLights() `should be equal to` 1

        f.calcNextState()
        f.switch()
        f.numberOfOnLights() `should be equal to` 1
    }
}
