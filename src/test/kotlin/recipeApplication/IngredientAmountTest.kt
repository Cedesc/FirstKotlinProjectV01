package recipeApplication

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class IngredientAmountTest {

    private lateinit var ingredientAmount1: IngredientAmount
    private lateinit var ingredientAmount2: IngredientAmount

    @Before
    fun setUp() {
        ingredientAmount1 = IngredientAmount(Ingredient.EGG, 2)
        ingredientAmount2 = IngredientAmount(Ingredient.BUTTER, 0.5, "EL")
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testToString() {
        assertEquals("2 Egg(s)", ingredientAmount1.toString())
        assertEquals("0.5 EL Butter", ingredientAmount2.toString())
    }

    @Test
    fun getIngredient() {
        assertEquals(Ingredient.EGG, ingredientAmount1.ingredient)
        assertEquals(Ingredient.BUTTER, ingredientAmount2.ingredient)
    }

    @Test
    fun getAmount() {
        assertEquals(2.0, ingredientAmount1.amount, 0.0)
        assertEquals(0.5, ingredientAmount2.amount, 0.0)
    }
}