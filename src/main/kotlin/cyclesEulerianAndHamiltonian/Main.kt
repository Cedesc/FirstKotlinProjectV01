package cyclesEulerianAndHamiltonian


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
    for (i in 1..5) {
        hGraphPaper.nodes.shuffle()
        println("$i. Hamiltonian Cycle (shuffled): ${hGraphPaper.findHamiltonianCycle()}")
    }

    // complete graph of arbitrary size
    val sizeComplete = 9      // size 11 needs few seconds, size 12 need about 46 seconds
    val hGraphComplete: GraphHamiltonianCycle = createCompleteHamiltonianGraph(sizeComplete)
    println("\nHamilton Cycle of a complete graph of size $sizeComplete: ")
    // timer        startTimerH1 means "Hamiltonian Timer 1"
    val startTimerH1 = System.currentTimeMillis()
    println("  ${hGraphComplete.findHamiltonianCycle()}")
    println("Elapsed Time: ${(System.currentTimeMillis() - startTimerH1).toDouble() / 1000}s")

    // A random graph in which each node has three outgoing edges to
    // different nodes that also have an outgoing edge to the respective node
    val sizeSampleGraph = 15     // size 25 needs 5 seconds, size 30 needs 110 seconds
    val hGraphSample: GraphHamiltonianCycle = createSampleHamiltonianGraph(sizeSampleGraph)
    println("\nHamilton Cycle of a graph in a special form of size $sizeSampleGraph: ")
    // timer        startTimerH2 means "Hamiltonian Timer 2"
    val startTimerH2 = System.currentTimeMillis()
    println("  ${hGraphSample.findHamiltonianCycle()}")
    println("Elapsed Time: ${(System.currentTimeMillis() - startTimerH2).toDouble() / 1000}s")
    println("\n\n")



    // Eulerian Cycle
    println("------------------------EULERIAN------------------------\n")


    // example from the paper
    val paperExample: GraphEulerianCycle = createEulerianGraphFromThePaper()

    // example from the paper in a different order
    val paperExampleDifferentOrder: GraphEulerianCycle = createEulerianGraphFromThePaperInDifferentOrder()

    // concrete graph as example
    val concreteExample: GraphEulerianCycle = createEulerianGraphWithFourSubCycles()

    // random complete graph - max value about 65, stack overflow error for values above
    val complete: GraphEulerianCycle = createCompleteEulerianGraph(15)

    // random doubly linked list graph - max value about 3000, maybe 3500, errors for values above
    val doublyLinkedList: GraphEulerianCycle = createDoublyLinkedListEulerianGraph(2000)


    // change this to use other graph
    val eGraph: GraphEulerianCycle = paperExample

    // print graph
    println("$eGraph")

    // print Eulerian Cycle
    println("Eulerian Cycle")
    // timer        startTimerE1 means "Eulerian Timer 1"
    val startTimerE1 = System.currentTimeMillis()
    println("RESULT:       ${eGraph.findEulerianCycle(printSteps = true)}")
    println("Elapsed Time: ${(System.currentTimeMillis() - startTimerE1).toDouble() / 1000}s")

}
