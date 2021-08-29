package recipeApplication

class IngredientAmount(val ingredient: Ingredient, amount: Int, private val amountStrAddition: String = "") {

    var amount: Double = amount.toDouble()

    fun IngredientAmount(val ingredient: Ingredient, amount: Double, amountStrAddition: String = "") {

    }


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
