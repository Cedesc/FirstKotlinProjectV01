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
        // TODO: 31.08.2021
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
        assertEquals("0.5 Onion", recipe.ingredientAmounts[0].toString())
        assertEquals("50 g OatFlakes", recipe.ingredientAmounts[1].toString())
        assertEquals("90 ml VegetableBroth", recipe.ingredientAmounts[2].toString())
        assertEquals("0.25 Egg", recipe.ingredientAmounts[3].toString())
        assertEquals("some Butter", recipe.ingredientAmounts[4].toString())
        assertEquals("some SunflowerOil", recipe.ingredientAmounts[5].toString())
        assertEquals(Requirement.Pan, recipe.requirements[0])
        assertEquals(Tag.Easy, recipe.tags[0])
        assertEquals(Tag.Meal, recipe.tags[1])
        assertEquals("n/a", recipe.image)
        assertEquals(2, recipe.recommendedAmount)
    }
}