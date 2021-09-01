package recipeApplication

import recipeApplication.Ingredient as Ingr


class SavedRecipes {

    private val ingredientAmount01A: IngredientAmount = IngredientAmount(Ingr.EGG, 2)
    private val ingredientAmount01B: IngredientAmount = IngredientAmount(Ingr.BUTTER,"some")
    val recipe01: Recipe = Recipe("Scrambled Eggs",
        "Egg in the pan you know",
        5, 1,
        arrayListOf(ingredientAmount01A, ingredientAmount01B),
        arrayListOf(Requirement.PAN),
        arrayListOf(Tag.APPETIZER, Tag.EASY),
        "n/a", 1)


    private val ingredientAmount02A: IngredientAmount = IngredientAmount(Ingr.ONION, 1)
    private val ingredientAmount02B: IngredientAmount = IngredientAmount(Ingr.OAT_FLAKES, 100, "g")
    private val ingredientAmount02C: IngredientAmount = IngredientAmount(Ingr.VEGETABLE_BROTH, 180, "ml")
    private val ingredientAmount02D: IngredientAmount = IngredientAmount(Ingr.EGG, 0.5)
    private val ingredientAmount02E: IngredientAmount = IngredientAmount(Ingr.BUTTER, "some")
    private val ingredientAmount02F: IngredientAmount = IngredientAmount(Ingr.SUNFLOWER_OIL, "some")
    val recipe02: Recipe = Recipe("Burger Patties",
        "Finely chop the onion. \n" +
                "Saute the onion in butter. Add the oat flakes to the pan and deglaze with the vegetable broth. " +
                "Let the oatmeal mixture steep over very low heat for about 15min. \n" +
                "Then add the egg and mix it. Season with salt and pepper. \n" +
                "Shape the dough into burgers and fry the in oil until golden brown.",
        10, 15,
        arrayListOf(ingredientAmount02A, ingredientAmount02B, ingredientAmount02C, ingredientAmount02D,
            ingredientAmount02E, ingredientAmount02F),
        arrayListOf(Requirement.PAN),
        arrayListOf(Tag.MEAL, Tag.EASY),
        "n/a", 2)


    private val ingredientAmount03A: IngredientAmount = IngredientAmount(Ingr.TORTELLINI, 2, "Pck.")
    private val ingredientAmount03B: IngredientAmount = IngredientAmount(Ingr.CHAMPIGNONS, 400, "g")
    private val ingredientAmount03C: IngredientAmount = IngredientAmount(Ingr.CHEESE_GRATED, 250, "g")
    private val ingredientAmount03D: IngredientAmount = IngredientAmount(Ingr.ONION, 2)
    private val ingredientAmount03E: IngredientAmount = IngredientAmount(Ingr.TOMATOES_STRAINED, 1, "Pck.")
    private val ingredientAmount03F: IngredientAmount = IngredientAmount(Ingr.WHIPPED_CREAM, 1, "Pck.")
    val recipe03: Recipe = Recipe("Tortellini Casserole",
        "Cook the Tortellini. Cut the onions and clean the mushrooms. \n" +
                "Saute the onions. Add the cleaned mushrooms and fry them for about 5 minutes. " +
                "Add the whipped cream and the strained tomatoes. Season it. Let it boil. \n" +
                "Add the Tortellini to a casserole dish, then pour the sauce over it. " +
                "Then sprinkle over the grated cheese. \n" +
                "Bake for approx 15min at 200 degrees.",
        25, 15,
        arrayListOf(ingredientAmount03A, ingredientAmount03B, ingredientAmount03C, ingredientAmount03D,
            ingredientAmount03E, ingredientAmount03F),
        arrayListOf(Requirement.PAN, Requirement.POT, Requirement.OVEN, Requirement.CASSEROLE_DISH),
        arrayListOf(Tag.MEAL, Tag.DURABLE),
        "n/a", 4)
}
