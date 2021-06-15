// Interfaces
// It is possible that classes inherit from multiple interfaces but not from multiple classes!
interface someInterface {
    val firstVal: String
    val secondVal: String

    abstract fun firstFunc()
    fun secondFunc(): String {
        return "Hello! It is allowed to write a body in interface methods!"
    }
}


// Inheriting class
abstract class FatherOfTheJoonge() {

    abstract var name: String
        protected set

    protected open var height: Double = 2.0

    public fun catchphrase(): String {
        return "So ein Feuerball, Joonge!"
    }

    protected open fun printMe() {
        println("Get off my property!")
    }

}


class Joonge(name: String, protected override var height: Double = 1.62): FatherOfTheJoonge() {

    override var name: String = name
        get() = "It's name is \"$field\"" // Getter and Setter
        protected set(value) {
            if (value.length > 5) {
                field = value
            }
        }

    var numberOfArms: Int = 2
        get() = field
        private set

    // Secondary Constructor
    constructor(name: String, height: Double, numberOfArms: Int): this(name, height) {
        this.numberOfArms = numberOfArms
    }

    // Init-Block Constructor
    // Caution! Pay Attention to the Initialization of the variables!!
    // Methods can be used, but undefined variables get default values
    init {
        println("Hello World! $name has arrived!")
        printMe()
    }

    // Override
    protected override fun printMe() {
        println("Joonge, I am $name and I have $numberOfArms arms and I am " + this.height + "m, Joonge!")
    }

    override fun toString(): String {
        return "Joonge(height=$height, name='$name', numberOfArms=$numberOfArms)"
    }

    fun dropCatchphraseWithExtra(): String {
        return super.catchphrase() + " Und Finger weg von mein Nuggets! I am " + super.height + "m!"
    }


}




fun main(args: Array<String>) {
    val loki: Joonge = Joonge("The Boyyyy")
    val lauchBoy: Joonge = Joonge("Lauch Boy", 1.79, 3)

    println("\n")
    println(loki.name)
    println(loki)
    println(lauchBoy)

    println("\n")
    println(loki.catchphrase())
    println(loki.dropCatchphraseWithExtra())
}
