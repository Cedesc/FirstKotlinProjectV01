package recipeApplication

class DatabaseConnection {
    fun search(name: String, instruction: String, maximalWorkingTime: Int, maximalTotalTime: Int,
               maximalRequirements: ArrayList<Requirement>, maximalIngredients: ArrayList<Ingredient>,
               minimalIngredients: ArrayList<Ingredient>, notIngredients: ArrayList<Ingredient>,
               minimalTags: ArrayList<Tag>, notTags: ArrayList<Tag>): ArrayList<Recipe> {
        // TODO: 24.08.2021 implement function for databases
        return arrayListOf<Recipe>()
    }
}
