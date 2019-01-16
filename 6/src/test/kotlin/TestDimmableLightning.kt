import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.assertThrows
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@DisplayName("Test of dimmable winter lightning")
internal class TestDimmableLightning {

    private lateinit var lightning: DimmableLightning

    @BeforeTest
    fun init() {
        lightning = DimmableLightning(1)
    }

    @Test
    fun `should turn on the light`() {
        val c = Coordinate(0, 0)
        assertTrue { lightning.isOff(c) }
        lightning.turnOn(c)
        assertTrue { lightning.isOn(c) }
    }

    @Test
    fun `should turn off the light`() {
        val c = Coordinate(0, 0)
        assertTrue { lightning.isOff(c) }
        lightning.turnOn(c)
        assertTrue { lightning.isOn(c) }
        lightning.turnOff(c)
        assertFalse { lightning.isOn(c) }
    }

    @Test
    fun `should toggle the light - that is increase brightness by 2`() {
        val c = Coordinate(0, 0)
        assertTrue { lightning.isOff(c) }
        lightning.toggle(c)
        assertTrue { lightning.isOn(c) }
        assertEquals(2, lightning.countLights())
        lightning.toggle(c)
        assertTrue { lightning.isOn(c) }
        assertEquals(4, lightning.countLights())
    }

    @Test
    fun `should have no shining lights by default`() {
        assertEquals(0, lightning.countLights())
    }

    @Test
    fun `should have some shining light when it is on`() {
        val c = Coordinate(0, 0)
        assertEquals(0, lightning.countLights())
        lightning.toggle(c)
        assertEquals(2, lightning.countLights())
    }

    @Test
    fun `should generate coordinates`() {
        assertEquals(0, lightning.countLights())
    }

    @Nested
    @DisplayName("Testing checking on out of bounds lights")
    inner class TestingCheckingOutOfBounds {
        private val c = Coordinate(1, 1)

        @Test
        @DisplayName("is off")
        fun `should throw exception when checking off value out of lightning bounds`() {
            assertThrows<ArrayIndexOutOfBoundsException> { lightning.isOff(c) }
        }

        @Test
        @DisplayName("is on")
        fun `should throw exception when checking on value out of lightning bounds`() {
            assertThrows<ArrayIndexOutOfBoundsException> { lightning.isOn(c) }
        }
    }

    @Nested
    @DisplayName("Testing setting on out of bounds lights")
    inner class TestingSettingOutOfBounds {
        private val c = Coordinate(1, 1)

        @Test
        @DisplayName("setting off")
        fun `should throw exception when setting off value out of lightning bounds`() {
            assertThrows<ArrayIndexOutOfBoundsException> { lightning.turnOff(c) }
        }

        @Test
        @DisplayName("setting on")
        fun `should throw exception when setting on value out of lightning bounds`() {
            assertThrows<ArrayIndexOutOfBoundsException> { lightning.turnOn(c) }
        }

        @Test
        @DisplayName("toggling")
        fun `should throw exception when toggling value out of lightning bounds`() {
            assertThrows<ArrayIndexOutOfBoundsException> { lightning.toggle(c) }
        }
    }
}