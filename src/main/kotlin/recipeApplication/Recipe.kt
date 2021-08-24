package recipeApplication

class Recipe(val name: String, val image: String, val instruction: String, val workingTime: Int, val restingTime: Int,
             val ingredients: ArrayList<Ingredient>, val requirements: ArrayList<Requirement>,
             val tags: ArrayList<Tag>)
