package recipeApplication

enum class Tag {
    MEAL {override fun toString(): String = "meal"},
    APPETIZER {override fun toString(): String = "appetizer"},
    DESSERT {override fun toString(): String = "dessert"},
    VEGAN {override fun toString(): String = "vegan"},
    EASY {override fun toString(): String = "easy"},
    DURABLE {override fun toString(): String = "durable"}
}
