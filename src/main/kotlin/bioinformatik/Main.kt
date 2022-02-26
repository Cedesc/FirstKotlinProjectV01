package bioinformatik


fun main(args: Array<String>) {



    // Hamiltonian Cycle
    println("-----------------------HAMILTONIAN-----------------------\n")


    // Example from the paper
    val hGraphPaper: GraphHamiltonianCycle = createHamiltonianGraphFromThePaper()

    // print graph
    println("$hGraphPaper")

    // print Hamiltonian Cycle
    println("Hamiltonian Cycle: ${hGraphPaper.findHamiltonianCycle()}\n")

    // shuffle nodes 5 times
    for (i in 1 .. 5) {
        hGraphPaper.nodes.shuffle()
        println("$i. Hamiltonian Cycle (shuffled): ${hGraphPaper.findHamiltonianCycle()}")
    }

    // complete graph of arbitrary size
    val size = 6      // size 10 needs few seconds
    val hGraphComplete: GraphHamiltonianCycle = createCompleteHamiltonianGraph(size)
    println("\nHamilton Cycle of a complete graph of size $size: ")
    println("  ${hGraphComplete.findHamiltonianCycle()}\n\n")



    // Eulerian Cycle
    println("------------------------EULERIAN------------------------\n")


    // example from the paper
    val paperExample: GraphEulerianCycle = createEulerianGraphFromThePaper()

    // example from the paper in a different order
    val paperExampleDifferentOrder: GraphEulerianCycle = createEulerianGraphFromThePaperInDifferentOrder()

    // concrete graph as example
    val concreteExample: GraphEulerianCycle = createEulerianGraphWithFourSubCycles()

    // random complete graph - max value about 65, stack overflow error for values above
    val complete: GraphEulerianCycle = createCompleteEulerianGraph(10)

    // random doubly linked list graph - max value about 3000, maybe 3500, errors for values above
    val doublyLinkedList: GraphEulerianCycle = createDoublyLinkedListEulerianGraph(2000)


    // change this to use other graph
    val eGraph: GraphEulerianCycle = concreteExample

    // print graph
    println("$eGraph")

    // print Eulerian Cycle
    println("Eulerian Cycle")
    println("RESULT:       ${eGraph.findEulerianCycle(printSteps = true)}")

}

/*
for withFourSubCycles

Is eGraph1 balanced? (Should be true)   true
EULERIAN CYCLE
SubCycles: [1  0  1  3  1  2  1  4  1  5  1, 0  3  0  2  0  4  0  5  0, 3  2  3  4  3  5  3, 2  4  2  5  2, 4  5  4]
SubCyclesAsList: [[1, 0, 1, 3, 1, 2, 1, 4, 1, 5, 1], [0, 3, 0, 2, 0, 4, 0, 5, 0], [3, 2, 3, 4, 3, 5, 3], [2, 4, 2, 5, 2], [4, 5, 4]]
hahahaha:   [[1, 0, 3, 0, 2, 0, 4, 0, 5, 0, 1, 3, 1, 2, 1, 4, 1, 5, 1], [3, 2, 3, 4, 3, 5, 3], [2, 4, 2, 5, 2], [4, 5, 4]]
hahahaha:   [[1, 0, 3, 2, 3, 4, 3, 5, 3, 0, 2, 0, 4, 0, 5, 0, 1, 3, 1, 2, 1, 4, 1, 5, 1], [2, 4, 2, 5, 2], [4, 5, 4]]
hahahaha:   [[1, 0, 3, 2, 4, 2, 5, 2, 3, 4, 3, 5, 3, 0, 2, 0, 4, 0, 5, 0, 1, 3, 1, 2, 1, 4, 1, 5, 1], [4, 5, 4]]
hahahaha:   [[1, 0, 3, 2, 4, 5, 4, 2, 5, 2, 3, 4, 3, 5, 3, 0, 2, 0, 4, 0, 5, 0, 1, 3, 1, 2, 1, 4, 1, 5, 1]]
CycleAsList: [1, 0, 3, 2, 4, 5, 4, 2, 5, 2, 3, 4, 3, 5, 3, 0, 2, 0, 4, 0, 5, 0, 1, 3, 1, 2, 1, 4, 1, 5, 1]
RESULT:     1  0  3  2  4  5  4  2  5  2  3  4  3  5  3  0  2  0  4  0  5  0  1  3  1  2  1  4  1  5  1
 */

/*
for doublyLinkedList with size = 3

Is eGraph1 balanced? (Should be true)   true
EULERIAN CYCLE
SubCycles: [Node 0  Node 1  Node 0, Node 1  Node 2  Node 1, Node 3  Node 2  Node 3]
SubCyclesAsList: [[Node 0, Node 1, Node 0], [Node 1, Node 2, Node 1], [Node 3, Node 2, Node 3]]
hahahaha:   [[Node 0, Node 1, Node 2, Node 1, Node 0], [Node 3, Node 2, Node 3]]
hahahaha:   [[Node 0, Node 1, Node 2, Node 3, Node 2, Node 1, Node 0]]
CycleAsList: [Node 0, Node 1, Node 2, Node 3, Node 2, Node 1, Node 0]
RESULT:     Node 0  Node 1  Node 2  Node 3  Node 2  Node 1  Node 0
 */
