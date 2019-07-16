import Lighting.Command.TURN_OFF
import LightingProgram.UnrecognizedCommandException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

@DisplayName("Test of light program runner")
internal class TestLightingProgram {
    @Test
    fun `should understand the text command`() {
        val c = "turn off 150,300 through 213,999"

        val (command, from, to) = LightingProgram { emptyList() }.parseCommand(c)

        assertEquals(TURN_OFF, command)
        assertEquals(Coordinate(150, 300), from)
        assertEquals(Coordinate(213, 999), to)
    }

    @Test
    fun `should throw when command is unrecognized or ill formatted`() {
        val c = "turn back 150,300 through 213,999"
        assertThrows<UnrecognizedCommandException> { LightingProgram { emptyList() }.parseCommand(c) }
    }

    @Test
    fun `should throw when command is ill formatted`() {
        val c = "Lorem ipsum dolores"
        assertThrows<UnrecognizedCommandException> { LightingProgram { emptyList() }.parseCommand(c) }
    }
}