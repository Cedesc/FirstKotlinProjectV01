package recipeApplication

class ShowRecipe(recipe: Recipe) {

    val name: String = recipe.name
    val instruction: String = recipe.instruction
    val workingTime: Int = recipe.workingTime
    val restingTime: Int = recipe.restingTime
    val totalTime: Int = workingTime + restingTime
    val ingredientAmountsOriginal: ArrayList<IngredientAmount> = recipe.ingredientAmounts
    var ingredientAmountsActual: ArrayList<IngredientAmount> = copyIngredientAmounts(ingredientAmountsOriginal)
    val requirements: ArrayList<Requirement> = recipe.requirements
    val tags: ArrayList<Tag> = recipe.tags
    val image: String = recipe.image

    var portions: Int = 1
        set(value) {
            field = when {
                value > 10 -> 10
                value < 1 -> 1
                else -> value
            }
            for (ingrIndex: Int in ingredientAmountsActual.indices) {
                ingredientAmountsActual[ingrIndex].amount = field * ingredientAmountsOriginal[ingrIndex].amount
            }
        }

    init {
        portions = recipe.recommendedAmount
    }

    private fun copyIngredientAmounts(input: ArrayList<IngredientAmount>): ArrayList<IngredientAmount> {
        val res: ArrayList<IngredientAmount> = ArrayList()
        input.forEach {
            res.add(it.copy())
        }
        return res
    }

    override fun toString(): String {
        return "Name: $name \n" +
                "Instruction: $instruction \n" +
                "Total Time: $totalTime min  Working Time: $workingTime min  Resting Time: $restingTime min \n" +
                "Requirements: $requirements \n" +
                "Tags: $tags \n" +
                "Portions: $portions \n" +
                "Ingredients: $ingredientAmountsActual"
    }
}
