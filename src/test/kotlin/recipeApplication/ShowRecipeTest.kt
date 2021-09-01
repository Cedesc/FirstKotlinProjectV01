package recipeApplication

import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import recipeApplication.Ingredient as Ingr
import recipeApplication.Requirement as Reqs

class ShowRecipeTest {

    private lateinit var ingredientAmount1: IngredientAmount
    private lateinit var ingredientAmount2: IngredientAmount
    private lateinit var recipe: Recipe
    private lateinit var obj: ShowRecipe

    @Before
    fun setUp() {
        ingredientAmount1 = IngredientAmount(Ingr.EGG, 2)
        ingredientAmount2 = IngredientAmount(Ingr.BUTTER, 1, "EL")
        recipe = Recipe("Scrambled Eggs", "Egg in the pan you know",
            5, 1, arrayListOf(ingredientAmount1, ingredientAmount2),
            arrayListOf(Reqs.PAN), arrayListOf(Tag.EASY, Tag.APPETIZER), "n/a", 1)
        obj = ShowRecipe(recipe)
    }

    @After
    fun tearDown() {
    }


    @Test
    fun getName() {
        assertEquals("Scrambled Eggs", obj.name)
    }

    @Test
    fun getImage() {
        assertEquals("n/a", obj.image)
    }

    @Test
    fun getInstruction() {
        assertEquals("Egg in the pan you know", obj.instruction)
    }

    @Test
    fun getWorkingTime() {
        assertEquals(5, obj.workingTime)
    }

    @Test
    fun getRestingTime() {
        assertEquals(1, obj.restingTime)
    }

    @Test
    fun getTotalTime() {
        assertEquals(6, obj.totalTime)
    }

    @Test
    fun getIngredientAmountsOriginal() {
        assertEquals(arrayListOf(ingredientAmount1, ingredientAmount2), obj.ingredientAmountsOriginal)
        assertNotEquals(arrayListOf(ingredientAmount1), obj.ingredientAmountsOriginal)

        assertEquals(Ingr.EGG, obj.ingredientAmountsOriginal[0].ingredient)
        assertEquals(2.0, obj.ingredientAmountsOriginal[0].amount, 0.0)
        assertEquals(Ingr.BUTTER, obj.ingredientAmountsOriginal[1].ingredient)
        assertEquals(1.0, obj.ingredientAmountsOriginal[1].amount, 0.0)
    }

    @Test
    fun getIngredientAmountsActual() {
        assertEquals(obj.ingredientAmountsOriginal[0].ingredient,
                     obj.ingredientAmountsActual[0].ingredient)
        assertEquals(obj.ingredientAmountsOriginal[0].amount,
                     obj.ingredientAmountsActual[0].amount * obj.portions, 0.0)
        assertEquals(obj.ingredientAmountsOriginal[1].ingredient,
            obj.ingredientAmountsActual[1].ingredient)
        assertEquals(obj.ingredientAmountsOriginal[1].amount,
            obj.ingredientAmountsActual[1].amount * obj.portions, 0.0)
    }

    @Test
    fun getRequirements() {
        assertEquals(arrayListOf(Reqs.PAN), obj.requirements)
    }

    @Test
    fun getTags() {
        assertEquals(arrayListOf(Tag.EASY, Tag.APPETIZER), obj.tags)
    }

    @Test
    fun getPortions() {
        assertEquals(1, obj.portions)
    }

    @Test
    fun setPortions() {
        assertEquals(1, obj.portions)
        assertEquals(2.0, obj.ingredientAmountsActual[0].amount, 0.0)
        assertEquals(1.0, obj.ingredientAmountsActual[1].amount, 0.0)

        obj.portions += 1
        assertEquals(2, obj.portions)
        assertEquals(2.0 * 2, obj.ingredientAmountsActual[0].amount, 0.0)
        assertEquals(1.0 * 2, obj.ingredientAmountsActual[1].amount, 0.0)

        obj.portions = -5
        assertEquals(1, obj.portions)
        assertEquals(2.0 * 1, obj.ingredientAmountsActual[0].amount, 0.0)
        assertEquals(1.0 * 1, obj.ingredientAmountsActual[1].amount, 0.0)

        obj.portions = 100
        assertEquals(10, obj.portions)
        assertEquals(2.0 * 10, obj.ingredientAmountsActual[0].amount, 0.0)
        assertEquals(1.0 * 10, obj.ingredientAmountsActual[1].amount, 0.0)

        obj.portions = 5
        assertEquals(5, obj.portions)
        assertEquals(2.0 * 5, obj.ingredientAmountsActual[0].amount, 0.0)
        assertEquals(1.0 * 5, obj.ingredientAmountsActual[1].amount, 0.0)
    }

    @Test
    fun toStringTest() {
        assertEquals("Name: Scrambled Eggs \n" +
                "Instruction: Egg in the pan you know \n" +
                "Total Time: 6 min  Working Time: 5 min  Resting Time: 1 min \n" +
                "Requirements: [pan] \n" +
                "Tags: [easy, appetizer] \n" +
                "Portions: 1 \n" +
                "Ingredients: [2 Egg(s), 1 EL Butter]", obj.toString())
    }

}
