import org.amshove.kluent.invoking
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should throw`
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

internal class ReindeerTest {

    @Test
    @DisplayName("Should return raindeers race cycle time")
    fun getCycleTime() {
        Reindeer(1, 1, 1).cycleTime `should be equal to` 2

        Reindeer(42, 1, 1).cycleTime `should be equal to` 2
    }

    @Test
    fun `should get position within lifetime`() {
        val reindeer = Reindeer(100, 10, 10)
        reindeer.getPositionInLastCycle(0) `should be equal to` 0
        reindeer.getPositionInLastCycle(5) `should be equal to` 500
        reindeer.getPositionInLastCycle(10) `should be equal to` 1000
        reindeer.getPositionInLastCycle(15) `should be equal to` 1000
        reindeer.getPositionInLastCycle(20) `should be equal to` 1000
    }

    @Test
    fun `getting position behind time should throw exception`() {
        val reindeer = Reindeer(100, 10, 10)

        invoking { reindeer.getPositionInLastCycle(21) } `should throw` IllegalArgumentException::class
    }

    @Test
    fun `should calculate number of full cycles in given time`() {
        val reindeer = Reindeer(0, 50, 50)

        reindeer.fullCycles(0) `should be equal to` 0
        reindeer.fullCycles(1) `should be equal to` 0

        reindeer.fullCycles(100) `should be equal to` 1

        reindeer.fullCycles(101) `should be equal to` 1
        reindeer.fullCycles(200) `should be equal to` 2
    }

    @Test
    fun `should calculate reminder seconds`() {
        val reindeer = Reindeer(0, 50, 50)

        reindeer.reminderTime(0) `should be equal to` 0
        reindeer.reminderTime(1) `should be equal to` 1

        reindeer.reminderTime(100) `should be equal to` 0

        reindeer.reminderTime(666) `should be equal to` 66
        reindeer.reminderTime(200) `should be equal to` 0
    }

    @Test
    fun `should not move when spped is zero`() {
        val reindeer = Reindeer(0, 50, 50)

        reindeer.getPosition(10000) `should be equal to` 0
    }

    @Test
    fun `should move`() {
        val speed = 200
        val speedTime = 50
        val sleep = 50

        val reindeer = Reindeer(speed, speedTime, sleep)

        reindeer.getPosition(10000) `should be equal to` 100 * speed * speedTime
    }

    @Test
    fun `should move a bit further`() {
        val speed = 200
        val speedTime = 50
        val sleep = 50

        val reindeer = Reindeer(speed, speedTime, sleep)

        reindeer.getPosition(10001) `should be equal to` 100 * speed * speedTime + speed
    }
}
