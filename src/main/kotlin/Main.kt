fun main(args: Array<String>) {

    // Constants, variables and print commands
    val width: Int = 1920
    val height: Int = 1080
    println("Width: $width  Height: $height")

    var numberOfSillyThoughts: Int = 0
    numberOfSillyThoughts++
    print("Was? ")
    println(numberOfSillyThoughts)

    val pi: Double = 3.14 // Double: 64 Bit
    println("Hello World!")

    val foo: Boolean = (1920 == width) && ( !(height == 1920) || "Hallo"[4] == 'o')
    println(foo)


    // Arrays
    print("\n")
    var myArray = arrayOf(2, "So ein Feuerball!")
    println(myArray[1])
    myArray = Array(2){i -> i} // [0, 1]
    println(myArray[1])


    // if clauses
    print("\n")
    if (pi > 1) {
        println("Obvious")
    } else {
        println("Never!")
    }


    // for loops
    print("\n")
    val myString: String = "Hello World!"
    for (letter: Char in myString) {
        if (letter !in "aeiou") {
            print(letter + 3)
            print(" + ")
        }
    }
    println()
    for (index: Int in myString.indices) {
        print(" - $index - ")
    }


    // while loops
    print("\n")
    do {
        while (true) {
            break
        }
        continue
    } while (false)


    // user input and casting
    print("\n")
    print("What you type here will be printed below: ")
    val userInput: String = ""//readLine()
    println(userInput)
    print("Write a number: ")
    var numberInput = Integer.valueOf(1)//readLine())
    numberInput++
    println("Your number plus 1: $numberInput")



    // exercise integer overflow
    print("\n")
    var myInteger: Int = 2147483647
    // myInteger = 1
    while (myInteger > 0) {
        myInteger++
    }
    myInteger--
    println("Maximum size of an Integer: $myInteger")


    // Maybe Typen
    var myString1: String? = null
    println("Finger weg von meinen Nuggets: " + myString1?.length) // ? only execute if it is not null
    var myString2 = "Ok"
    println("Sooooooo ein Feuerball: " + myString2!!.length) // !! assume that it is not null

    println(myString1?.length ?:"String is null") // ?: if previous is null returns following
    val length: Int = myString1?.length ?: 0
    myString1?.let {
        println("If you see this in the console, myString1 is certainly not null!")
        // "let" if myString is not null, execute arbitrary code
    }

}