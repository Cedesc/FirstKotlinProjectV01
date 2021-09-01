package recipeApplication

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*


class SavedRecipesTest {

    lateinit var savedRecipes: SavedRecipes

    @Before
    fun setUp() {
        savedRecipes = SavedRecipes()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getRecipe01() {
        val recipe: Recipe = savedRecipes.recipe01
        assertEquals("Scrambled Eggs", recipe.name)
        assertEquals("Egg in the pan you know", recipe.instruction)
        assertEquals(5, recipe.workingTime)
        assertEquals(1, recipe.restingTime)
        assertEquals("2 Egg(s)", recipe.ingredientAmounts[0].toString())
        assertEquals("some Butter", recipe.ingredientAmounts[1].toString())
        assertEquals(Requirement.PAN, recipe.requirements[0])
        assertEquals(Tag.APPETIZER, recipe.tags[0])
        assertEquals(Tag.EASY, recipe.tags[1])
        assertEquals("n/a", recipe.image)
        assertEquals(1, recipe.recommendedAmount)
    }

    @Test
    fun getRecipe02() {
        val recipe: Recipe = savedRecipes.recipe02
        assertEquals("Burger Patties", recipe.name)
        assertEquals("Finely chop the onion. \n" +
                "Saute the onion in butter. Add the oat flakes to the pan and deglaze with the vegetable broth. " +
                "Let the oatmeal mixture steep over very low heat for about 15min. \n" +
                "Then add the egg and mix it. Season with salt and pepper. \n" +
                "Shape the dough into burgers and fry the in oil until golden brown.", recipe.instruction)
        assertEquals(10, recipe.workingTime)
        assertEquals(15, recipe.restingTime)
        assertEquals("0.5 Onion(s)", recipe.ingredientAmounts[0].toString())
        assertEquals("50 g Oat Flakes", recipe.ingredientAmounts[1].toString())
        assertEquals("90 ml Vegetable Broth", recipe.ingredientAmounts[2].toString())
        assertEquals("0.25 Egg(s)", recipe.ingredientAmounts[3].toString())
        assertEquals("some Butter", recipe.ingredientAmounts[4].toString())
        assertEquals("some Sunflower Oil", recipe.ingredientAmounts[5].toString())
        assertEquals(Requirement.PAN, recipe.requirements[0])
        assertEquals(Tag.MEAL, recipe.tags[0])
        assertEquals(Tag.EASY, recipe.tags[1])
        assertEquals("n/a", recipe.image)
        assertEquals(2, recipe.recommendedAmount)
    }

    @Test
    fun getRecipe03() {
        val recipe: Recipe = savedRecipes.recipe03
        assertEquals("Tortellini Casserole", recipe.name)
        assertEquals("Cook the Tortellini. Cut the onions and clean the mushrooms. \n" +
                "Saute the onions. Add the cleaned mushrooms and fry them for about 5 minutes. " +
                "Add the whipped cream and the strained tomatoes. Season it. Let it boil. \n" +
                "Add the Tortellini to a casserole dish, then pour the sauce over it. " +
                "Then sprinkle over the grated cheese. \n" +
                "Bake for approx 15min at 200 degrees.", recipe.instruction)
        assertEquals(25, recipe.workingTime)
        assertEquals(15, recipe.restingTime)
        assertEquals("0.5 Pck. Tortellini", recipe.ingredientAmounts[0].toString())
        assertEquals("100 g Champignons", recipe.ingredientAmounts[1].toString())
        assertEquals("62.5 g Grated Cheese", recipe.ingredientAmounts[2].toString())
        assertEquals("0.5 Onion(s)", recipe.ingredientAmounts[3].toString())
        assertEquals("0.25 Pck. Strained Tomatoes", recipe.ingredientAmounts[4].toString())
        assertEquals("0.25 Pck. Whipped Cream", recipe.ingredientAmounts[5].toString())
        assertEquals(Requirement.PAN, recipe.requirements[0])
        assertEquals(Requirement.POT, recipe.requirements[1])
        assertEquals(Requirement.OVEN, recipe.requirements[2])
        assertEquals(Requirement.CASSEROLE_DISH, recipe.requirements[3])
        assertEquals(Tag.MEAL, recipe.tags[0])
        assertEquals(Tag.DURABLE, recipe.tags[1])
        assertEquals("n/a", recipe.image)
        assertEquals(4, recipe.recommendedAmount)
    }
}