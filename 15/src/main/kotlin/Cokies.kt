import Ingredient.IngredientProperty
import com.google.common.io.Resources

const val TEASPOONS_CAPACITY = 100

@Suppress("UnstableApiUsage")
fun main() {
    val allIngredients: List<Ingredient> = Resources.getResource("input.txt")
        .readText().lines()
        .map(Ingredient.Companion::fromString)

    val allSolutions = ReceiptGenerator.generate(TEASPOONS_CAPACITY, allIngredients.size)

    val cookieScoringFunction = { receipt: List<Int> -> calcSingleReceiptScore(receipt, allIngredients) }

    val noFiltering: (List<Int>) -> Boolean = { true }
    val maxCookieByNonCalories =
        CookiesCalculator.calcBestCookie(
            allSolutions,
            allIngredients,
            noFiltering,
            cookieScoringFunction,
            maxScore()
        )
    println("Best cookie by non-calories is scored at $maxCookieByNonCalories")

    val max500CaloriesCookieByNonCalories =
        CookiesCalculator.calcBestCookie(
            allSolutions, allIngredients, only500calories(allIngredients), cookieScoringFunction,
            maxScore()
        )
    println("Best 500 calories cookie is scored at $max500CaloriesCookieByNonCalories")
}

private fun maxScore(): (List<Int>) -> Int = { it.maxOrNull()!! }

const val CALORIES_TARGET_500 = 500
private fun only500calories(allIngredients: List<Ingredient>): (List<Int>) -> Boolean = {
    calcSingleReceiptCaloriesValue(it, allIngredients) == CALORIES_TARGET_500
}

private fun calcSingleReceiptScore(receipt: List<Int>, ingredients: List<Ingredient>): Int {
    return getPropertyForIngredient(receipt, ingredients, IngredientProperty.CAPACITY) *
        getPropertyForIngredient(receipt, ingredients, IngredientProperty.DURABILITY) *
        getPropertyForIngredient(receipt, ingredients, IngredientProperty.FLAVOR) *
        getPropertyForIngredient(receipt, ingredients, IngredientProperty.TEXTURE)
}

private fun calcSingleReceiptCaloriesValue(receipt: List<Int>, ingredients: List<Ingredient>): Int {
    return getPropertyForIngredient(receipt, ingredients, IngredientProperty.CALORIES)
}

private fun getPropertyForIngredient(
    receipt: List<Int>,
    ingredients: List<Ingredient>,
    property: IngredientProperty
): Int {
    return receipt.zip(ingredients.map { it.getProperty(property) })
        .map { (spoons, value) -> spoons * value }
        .sum()
        .coerceAtLeast(0)
}

object CookiesCalculator {
    fun calcBestCookie(
        allRecipes: Set<List<Int>>,
        ingredients: List<Ingredient>,
        solutionFilter: (List<Int>) -> Boolean,
        cookieScoringFunction: (List<Int>) -> Int,
        bestCookieSelector: (List<Int>) -> Int
    ): Int {
        require(allRecipes.map { it.size }.all { it == ingredients.size }) { "All recipes must use all ingredients" }
        require(allRecipes.isNotEmpty())

        return allRecipes
            .filter(solutionFilter)
            .map(cookieScoringFunction)
            .let(bestCookieSelector)
    }
}

object ReceiptGenerator {
    fun generate(teaspoonsCapacity: Int, numberOfIngredients: Int): Set<List<Int>> {
        var result = setOf(List(numberOfIngredients) { 0 })

        repeat(teaspoonsCapacity) { result = result.flatMap { addSpoonToSingleIngredient(it) }.toSet() }

        return result
    }

    private fun addSpoonToSingleIngredient(solution: List<Int>): List<List<Int>> {
        return solution.withIndex()
            .map { (index, value) -> solution.toMutableList().apply { this[index] = value + 1 } }
    }
}
