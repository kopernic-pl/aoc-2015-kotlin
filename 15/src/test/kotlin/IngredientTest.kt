import Ingredient.IngredientProperty.CALORIES
import Ingredient.IngredientProperty.CAPACITY
import Ingredient.IngredientProperty.DURABILITY
import Ingredient.IngredientProperty.FLAVOR
import Ingredient.IngredientProperty.TEXTURE
import org.amshove.kluent.`should be equal to`
import kotlin.test.Test

internal class IngredientTest {

    @Test
    fun `should get properties by enum`() {
        val capacity = 1
        val durability = 2
        val flavor = 4
        val texture = 8
        val calories = 16

        val i = Ingredient("whateverName", capacity, durability, flavor, texture, calories)

        i.getProperty(CAPACITY) `should be equal to` capacity
        i.getProperty(DURABILITY) `should be equal to` durability
        i.getProperty(FLAVOR) `should be equal to` flavor
        i.getProperty(TEXTURE) `should be equal to` texture
        i.getProperty(CALORIES) `should be equal to` calories
    }

    @Test
    fun `should parse ingredient from string`() {
        val ingredientString = "Chocolate: capacity 0, durability 0, flavor 5, texture -1, calories 8"

        val i = Ingredient.fromString(ingredientString)

        i.name `should be equal to` "Chocolate"
        i.getProperty(CAPACITY) `should be equal to` 0
        i.getProperty(DURABILITY) `should be equal to` 0
        i.getProperty(FLAVOR) `should be equal to` 5
        i.getProperty(TEXTURE) `should be equal to` -1
        i.getProperty(CALORIES) `should be equal to` 8
    }
}
