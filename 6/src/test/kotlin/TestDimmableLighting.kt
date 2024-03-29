import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.assertThrows
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@DisplayName("Test of dimmable winter lighting")
internal class TestDimmableLighting {

    private lateinit var lighting: DimmableLighting

    @BeforeTest
    fun init() {
        lighting = DimmableLighting(1)
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
    fun `should toggle the light - that is increase brightness by 2`() {
        val c = Coordinate(0, 0)
        assertTrue { lighting.isOff(c) }
        lighting.toggle(c)
        assertTrue { lighting.isOn(c) }
        assertEquals(2, lighting.countLights())
        lighting.toggle(c)
        assertTrue { lighting.isOn(c) }
        assertEquals(4, lighting.countLights())
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
        assertEquals(2, lighting.countLights())
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
