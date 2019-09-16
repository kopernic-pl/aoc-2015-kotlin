import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotSame
import HouseVisitsSantaTracker.zeroLoc as init

class HouseVisitsSantaTrackerKtTest {

    @Test
    fun shouldMoveNorth() {
        val c = '^'
        assertEquals(Location(0, 1), HouseVisitsSantaTracker.move(init, c))
    }

    @Test
    fun shouldMoveSouth() {
        val c = 'v'
        assertEquals(Location(0, -1), HouseVisitsSantaTracker.move(init, c))
    }

    @Test
    fun shouldMoveEast() {
        val c = '>'
        assertEquals(Location(1, 0), HouseVisitsSantaTracker.move(init, c))
    }

    @Test
    fun shouldMoveWest() {
        val c = '<'
        assertEquals(Location(-1, 0), HouseVisitsSantaTracker.move(init, c))
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrow_whenMoveOperatorWrong() {
        val c = 'b'
        HouseVisitsSantaTracker.move(Location(0, 0), c)
    }

    @Test
    fun shouldMoveWestAndTrackPosition() {
        val c = '<'
        val initialLocationsList = listOf(init)
        val movePath = HouseVisitsSantaTracker.moveWithPath(initialLocationsList, c)
        assertEquals(listOf(init, Location(-1, 0)), movePath)
        assertNotSame(initialLocationsList, movePath)
    }

    @Test
    fun shouldMoveSanta() {
        assertEquals(2, HouseVisitsSantaTracker.aoc3a(">"))
        assertEquals(4, HouseVisitsSantaTracker.aoc3a("^>v<"))
        assertEquals(2, HouseVisitsSantaTracker.aoc3a("^v^v^v^v^v"))
    }

    @Test
    fun shouldMoveMechaSanta() {
        assertEquals(3, HouseVisitsSantaTracker.aoc3b("^v"))
        assertEquals(3, HouseVisitsSantaTracker.aoc3b("^>v<"))
        assertEquals(11, HouseVisitsSantaTracker.aoc3b("^v^v^v^v^v"))
    }
}
