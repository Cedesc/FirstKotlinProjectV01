package recipeApplication

enum class Requirement {
    POT {override fun toString(): String = "pot"},
    PAN {override fun toString(): String = "pan"},
    OVEN {override fun toString(): String = "oven"},
    CASSEROLE_DISH {override fun toString(): String = "casserole dish"}
}
