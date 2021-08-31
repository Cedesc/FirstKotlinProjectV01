package recipeApplication

import kotlin.math.roundToInt

class IngredientAmount(val ingredient: Ingredient, var amount: Double, private var amountStrAddition: String = "") {

    constructor(ingredient: Ingredient, amount: Int, amountStrAddition: String = ""):
            this(ingredient, amount.toDouble(), amountStrAddition)

    constructor(ingredient: Ingredient, amountStrAddition: String):
            this(ingredient, 0.0, amountStrAddition)


    override fun toString(): String {
        // if amount is an Integer, ".0" is removed
        val amountString: String =
            if (amount == amount.roundToInt().toDouble())
                amount.toInt().toString()
        else amount.toString()

        return if (amountStrAddition == "")
            // e.g. "5 Egg"
            "$amountString $ingredient"
        else {
            if (amount == 0.0)
                // e.g. "some Butter"
                "$amountStrAddition $ingredient"
            else
                // e.g. "100ml VegetableBroth"
                "$amountString $amountStrAddition $ingredient"
        }
    }

    fun copy(): IngredientAmount {
        return IngredientAmount(ingredient, amount, amountStrAddition)
    }
}
