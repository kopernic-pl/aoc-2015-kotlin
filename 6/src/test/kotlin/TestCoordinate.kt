import Coordinate.Companion.generateRange
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertTrue

internal class TestCoordinate {

    @Test
    fun `should generate one coordinate`() {
        assertEquals(
            listOf(Coordinate(0, 0)),
            generateRange(Coordinate(0, 0), Coordinate(0, 0)).toList()
        )
    }

    @Test
    fun `should generate two coordinates`() {
        assertEquals(
            listOf(Coordinate(0, 0), Coordinate(0, 1)),
            generateRange(Coordinate(0, 0), Coordinate(0, 1)).toList()
        )
    }

    @Test
    fun `should generate many coordinates`() {
        val generatedCommands = generateRange(Coordinate(0, 0), Coordinate(4, 4)).toList()
        assertEquals(generatedCommands.size, 25)
        assertTrue {
            generatedCommands.containsAll(
                setOf(
                    Coordinate(0, 0),
                    Coordinate(4, 0),
                    Coordinate(0, 4),
                    Coordinate(4, 4)
                )
            )
        }
    }

    @Test
    fun `should throw exception when generating backwards`() {
        assertThrows<IllegalArgumentException> {
            generateRange(
                Coordinate(4, 4),
                Coordinate(0, 0)
            )
        }
    }
}
