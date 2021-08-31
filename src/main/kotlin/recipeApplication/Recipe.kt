package recipeApplication

class Recipe(val name: String, val instruction: String, val workingTime: Int, val restingTime: Int,
             val ingredientAmounts: ArrayList<IngredientAmount>, val requirements: ArrayList<Requirement>,
             val tags: ArrayList<Tag>, val image: String, val recommendedAmount: Int = 1) {
    init {
        for (ingredientAmount: IngredientAmount in ingredientAmounts) {
            ingredientAmount.amount = ingredientAmount.amount / recommendedAmount
        }
    }
}
