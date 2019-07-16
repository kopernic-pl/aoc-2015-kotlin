import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.assertThrows
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@DisplayName("Test of switchable winter lighting")
internal class TestSwitchableLighting {

    private lateinit var lighting: SwitchableLighting

    @BeforeTest
    fun init() {
        lighting = SwitchableLighting(1)
    }

    @Test
    fun `should turn on the light`() {
        val c = Coordinate(0, 0)
        assertTrue { lighting.isOff(c) }
        lighting.turnOn(c)
        assertTrue { lighting.isOn(c) }
    }

    @Test
    fun `should turn off the light`() {
        val c = Coordinate(0, 0)
        assertTrue { lighting.isOff(c) }
        lighting.turnOn(c)
        assertTrue { lighting.isOn(c) }
        lighting.turnOff(c)
        assertFalse { lighting.isOn(c) }
    }

    @Test
    fun `should toggle the light`() {
        val c = Coordinate(0, 0)
        assertTrue { lighting.isOff(c) }
        lighting.toggle(c)
        assertTrue { lighting.isOn(c) }
        lighting.toggle(c)
        assertFalse { lighting.isOn(c) }
    }

    @Test
    fun `should have no shining lights by default`() {
        assertEquals(0, lighting.countLights())
    }

    @Test
    fun `should have some shining light when it is on`() {
        val c = Coordinate(0, 0)
        assertEquals(0, lighting.countLights())
        lighting.toggle(c)
        assertEquals(1, lighting.countLights())
    }

    @Test
    fun `should generate coordinates`() {
        assertEquals(0, lighting.countLights())
    }

    @Nested
    @DisplayName("Testing checking on out of bounds lights")
    inner class TestingCheckingOutOfBounds {
         private val c = Coordinate(1, 1)

        @Test
        @DisplayName("is off")
        fun `should throw exception when checking off value out of lighting bounds`() {
            assertThrows<ArrayIndexOutOfBoundsException> { lighting.isOff(c) }
        }

        @Test
        @DisplayName("is on")
        fun `should throw exception when checking on value out of lighting bounds`() {
            assertThrows<ArrayIndexOutOfBoundsException> { lighting.isOn(c) }
        }
    }

    @Nested
    @DisplayName("Testing setting on out of bounds lights")
    inner class TestingSettingOutOfBounds {
        private val c = Coordinate(1, 1)

        @Test
        @DisplayName("setting off")
        fun `should throw exception when setting off value out of lighting bounds`() {
            assertThrows<ArrayIndexOutOfBoundsException> { lighting.turnOff(c) }
        }

        @Test
        @DisplayName("setting on")
        fun `should throw exception when setting on value out of lighting bounds`() {
            assertThrows<ArrayIndexOutOfBoundsException> { lighting.turnOn(c) }
        }

        @Test
        @DisplayName("toggling")
        fun `should throw exception when toggling value out of lighting bounds`() {
            assertThrows<ArrayIndexOutOfBoundsException> { lighting.toggle(c) }
        }
    }
}

