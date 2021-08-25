package recipeApplication

class IngredientAmount(val ingredient: Ingredient, var amount: Int, private val amountStrAddition: String = "") {

    override fun toString(): String {
        return if (amountStrAddition == "")
            "$amount $ingredient"
        else
            "$amount $amountStrAddition $ingredient"
    }

    fun copy(): IngredientAmount {
        return IngredientAmount(ingredient, amount, amountStrAddition)
    }
}
