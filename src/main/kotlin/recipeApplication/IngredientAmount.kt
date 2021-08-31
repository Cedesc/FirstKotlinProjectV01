package recipeApplication

import kotlin.math.roundToInt

class IngredientAmount(val ingredient: Ingredient, var amount: Double, private var amountStrAddition: String = "") {

    constructor(ingredient: Ingredient, amount: Int, amountStrAddition: String = ""):
            this(ingredient, amount.toDouble(), amountStrAddition)


    override fun toString(): String {
        // if amount is an Integer, ".0" is removed
        val amountString: String =
            if (amount == amount.roundToInt().toDouble())
                amount.toInt().toString()
        else amount.toString()

        return if (amountStrAddition == "")
            "$amountString $ingredient"
        else
            "$amountString $amountStrAddition $ingredient"
    }

    fun copy(): IngredientAmount {
        return IngredientAmount(ingredient, amount, amountStrAddition)
    }
}
