// Inheriting class
open class FatherOfTheJoonge() {
    protected fun catchphrase() {
        println("So ein Feuerball, Joonge!")
    }
}


class Joonge(name: String, private var height: Double = 1.62): FatherOfTheJoonge() {

    var name: String = name
        get() = "It's name is \"$field\"" // Getter and Setter
        private set(value) {
            if (value.length > 5) {
                field = value
            }
        }

    var numberOfArms: Int = 2
        private set

    // Secondary Constructor
    constructor(name: String, height: Double, numberOfArms: Int): this(name, height) {
        this.numberOfArms = numberOfArms
    }

    // Init-Block Constructor
    init {
        println("Hello World!")
        printMe()
    }

    private fun printMe() {
        println("Joonge, I am $name and I have $numberOfArms arms and I am " + this.height + "m, Joonge!")
    }

    override fun toString(): String {
        return "Joonge(height=$height, name='$name', numberOfArms=$numberOfArms)"
    }


}




fun main(args: Array<String>) {
    val loki: Joonge = Joonge("The other boy")
    val lauchBoy: Joonge = Joonge("Lauch Boy", 1.79, 3)
    println(loki.name)
    println(loki)
    println(lauchBoy)
}
