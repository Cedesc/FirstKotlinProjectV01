import recipeApplication.*
import recipeApplication.Ingredient as Ingr

class SavedRecipes {

    val ingredientAmount01A: IngredientAmount = IngredientAmount(Ingr.Egg, 2.0)
    val ingredientAmount01B: IngredientAmount = IngredientAmount(Ingr.Butter, 1.0, "EL")
    val recipe01: Recipe = Recipe("Scrambled Eggs", "Egg in the pan you know",
        5, 1, arrayListOf(ingredientAmount01A, ingredientAmount01B),
        arrayListOf(Requirement.Pan), arrayListOf(Tag.Easy, Tag.Appetizer), "n/a")

    val ingredientAmount02A: IngredientAmount = IngredientAmount(Ingr.Onion, 0.5)
    val ingredientAmount02B: IngredientAmount = IngredientAmount(Ingr.OatFlakes, 100.0, "g")
//    val ingredientAmount02C: IngredientAmount = IngredientAmount(
//    val ingredientAmount02D: IngredientAmount = IngredientAmount(
//    val ingredientAmount02E: IngredientAmount = IngredientAmount(
//    val ingredientAmount02F: IngredientAmount = IngredientAmount(
//    val ingredientAmount02G: IngredientAmount = IngredientAmount(

}
