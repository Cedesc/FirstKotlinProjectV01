package recipeApplication

class Recipe(val name: String, val instruction: String, val workingTime: Int, val restingTime: Int,
             val ingredientAmounts: ArrayList<IngredientAmount>, val requirements: ArrayList<Requirement>,
             val tags: ArrayList<Tag>, val image: String)
