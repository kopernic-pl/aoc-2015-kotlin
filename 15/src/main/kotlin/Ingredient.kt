class Ingredient(
    val name: String,
    private val capacity: Int,
    private val durability: Int,
    private val flavor: Int,
    private val texture: Int,
    private val calories: Int
) {
    enum class IngredientProperty {
        CAPACITY, DURABILITY, FLAVOR, TEXTURE, CALORIES
    }

    companion object {
        fun fromString(s: String): Ingredient {
            val regEx =
                ("(?<name>.+): capacity (?<capacityVal>[-+]?\\d*), " +
                        "durability (?<durabilityVal>[-+]?\\d*), flavor (?<flavorVal>[-+]?\\d*), " +
                        "texture (?<textureVal>[-+]?\\d*), calories (?<caloriesVal>[-+]?\\d*)")
                    .toRegex()
            return regEx
                .matchEntire(s)
                ?.groups
                .let { groups ->
                    Ingredient(
                        groups?.get("name")?.value!!,
                        groups["capacityVal"]?.value?.toInt()!!,
                        groups["durabilityVal"]?.value?.toInt()!!,
                        groups["flavorVal"]?.value?.toInt()!!,
                        groups["textureVal"]?.value?.toInt()!!,
                        groups["caloriesVal"]?.value?.toInt()!!
                    )
                }
        }
    }

    fun getProperty(p: IngredientProperty): Int {
        return when (p) {
            IngredientProperty.CAPACITY -> capacity
            IngredientProperty.DURABILITY -> durability
            IngredientProperty.FLAVOR -> flavor
            IngredientProperty.TEXTURE -> texture
            IngredientProperty.CALORIES -> calories
        }
    }

    override fun toString(): String {
        return "Ingredient(name='$name', " +
                "capacity=$capacity, " +
                "durability=$durability, " +
                "flavor=$flavor, " +
                "texture=$texture, " +
                "calories=$calories)"
    }
}
