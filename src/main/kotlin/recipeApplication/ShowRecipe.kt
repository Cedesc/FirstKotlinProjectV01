package recipeApplication

class ShowRecipe(private val recipe: Recipe, var portions: Int) {

    val name: String = recipe.name
    val image: String = recipe.image
    val instruction: String = recipe.instruction
    val workingTime: Int = recipe.workingTime
    val restingTime: Int = recipe.restingTime
    val totalTime: Int = workingTime + restingTime
    val ingredients: ArrayList<Ingredient> = recipe.ingredients
    val requirements: ArrayList<Requirement> = recipe.requirements
    val tags: ArrayList<Tag> = recipe.tags

//    fun ingredientsWithNewAmounts(ingredients: ArrayList<Ingredient>): ArrayList<Ingredient> {
//        for (ingredient: Ingredient in ingredients) {
//
//        }
//    }

    fun changePortionsAmount(newAmount: Int): Unit {
        // TODO: 24.08.2021 implement function
    }

    fun convertToText(): String {
        // TODO: 24.08.2021 implement function
        return ""
    }
}
