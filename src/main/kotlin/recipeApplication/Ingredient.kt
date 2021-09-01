package recipeApplication

enum class Ingredient {
    ONION {override fun toString(): String = "Onion(s)"},
    OAT_FLAKES {override fun toString(): String = "Oat Flakes"},
    VEGETABLE_BROTH {override fun toString(): String = "Vegetable Broth"},
    EGG {override fun toString(): String = "Egg(s)"},
    BUTTER {override fun toString(): String = "Butter"},
    OLIVE_OIL {override fun toString(): String = "Olive Oil"},
    SUNFLOWER_OIL {override fun toString(): String = "Sunflower Oil"},
    TORTELLINI {override fun toString(): String = "Tortellini"},
    CHAMPIGNONS {override fun toString(): String = "Champignons"},
    CHEESE_GRATED {override fun toString(): String = "Grated Cheese"},
    TOMATOES_STRAINED {override fun toString(): String = "Strained Tomatoes"},
    WHIPPED_CREAM {override fun toString(): String = "Whipped Cream"}
}

/*
OatFlakes - Haferflocken
VegetableBroth - Gemüsebrühe
CheeseGrated - geriebener Käse
TomatoesStrained - passierte Tomaten
WhippedCream - Schlagsahne
 */

// TODO: 31.08.2021 are Enums able to inherit?
