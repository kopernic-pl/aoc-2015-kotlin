import org.junit.Assert.assertEquals
import kotlin.test.Test

class GiftBoxKtTest {

    companion object {
        private const val ONE_SIZE_CUBE_WRAPPING_AREA = 6 * 1 + 1
    }

    @Test
    fun shouldCreateEmptyBox() {
        val box = GiftBox.create()
        assertEquals(0, box.wrappingArea)
        assertEquals(0, box.ribbonNeeded)
    }

    @Test
    fun shouldCreateCubeBox() {
        val box = GiftBox.create(1, 1, 1)
        assertEquals(ONE_SIZE_CUBE_WRAPPING_AREA, box.wrappingArea)
    }

    @Test(expected = RuntimeException::class)
    fun shouldFailCreatingBox_whenOnlyOneDimensionGiven() {
        GiftBox.create("1x")
    }

    @Test
    fun shouldCreateCubeBoxFromText() {
        val box = GiftBox.create("1x1x1")
        assertEquals(ONE_SIZE_CUBE_WRAPPING_AREA, box.wrappingArea)
        assertEquals(5, box.ribbonNeeded)
    }

    @Test
    fun shouldCreateRectangularPrismFromText() {
        val box = GiftBox.create("3x4x5")
        assertEquals(106, box.wrappingArea)
        assertEquals(14 + 3 * 4 * 5, box.ribbonNeeded)
    }

    @Test(expected = NumberFormatException::class)
    fun shouldThrowException_whenTooManyDimensions() {
        GiftBox.create("1x1x1x1000x2000x3000")
    }
}
