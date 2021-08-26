import org.amshove.kluent.`should be equal to`
import kotlin.test.Test

internal class ReformatterTest {

    @Test
    fun `should remove all objects with red valued property`() {
        val testString = """
            [{
                "a":1,
                "b":"red",
                "c":3
            },
            [1,2,3]]
        """

        Reformatter().reformat(testString) `should be equal to` "[{},[1,2,3]]"
    }

    @Test
    fun `should not remove red value from arrays`() {
        val testString = """
            [{
                "a":1,
                "b":"red",
                "c":3
            },
            [1,"red",3]]
        """

        Reformatter().reformat(testString) `should be equal to` """[{},[1,"red",3]]"""
    }
}
