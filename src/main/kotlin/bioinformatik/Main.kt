package bioinformatik

/**
 * Returns a complete graph of arbitrary size.
 */
fun createCompleteHamiltonianGraph(size: Int): GraphHamiltonianCycle {

    // create Array filled with null
    val newNodes: Array<NodeHamiltonianCycle?> = Array(size) {null}

    // fill the array with nodes
    for (i in 0 until size) {
        // for this task, it's not important how long the k-mers are or if there are duplicates
        newNodes[i] = NodeHamiltonianCycle("Node $i")
    }

    // cast the nodes of the array to a not nullable type
    val resultingNodes: List<NodeHamiltonianCycle> = newNodes.map { i -> i!! }

    // create outgoing edges to every other node except the node itself
    for (nodeIndex in resultingNodes.indices)
        resultingNodes[nodeIndex].outgoingEdges = resultingNodes - resultingNodes[nodeIndex]

    return GraphHamiltonianCycle(resultingNodes.shuffled().toTypedArray())
}

/**
 *
 */
fun createCompleteEulerianGraph(size: Int): GraphEulerianCycle {

    // create Array filled with null
    val newNodes: Array<NodeEulerianCycle?> = Array(size) {null}

    // fill the array with nodes
    for (i in 0 until size) {
        // for this task, it's not important how long the k-mers are or if there are duplicates
        newNodes[i] = NodeEulerianCycle("Node $i")
    }

    // cast the nodes of the array to a not nullable type
    val resultingNodes: List<NodeEulerianCycle> = newNodes.map { i -> i!! }

    // create outgoing edges to every other node except the node itself
    for (nodeIndex in resultingNodes.indices)
        resultingNodes[nodeIndex].outgoingEdges = resultingNodes - resultingNodes[nodeIndex]

    return GraphEulerianCycle(resultingNodes.shuffled().toTypedArray())
}

/**
 *
 */
fun createDoublyLinkedListEulerianGraph(size: Int): GraphEulerianCycle {

    // create Array filled with null
    val newNodes: Array<NodeEulerianCycle?> = Array(size) {null}

    // fill the array with nodes
    for (i in 0 until size) {
        // for this task, it's not important how long the k-mers are or if there are duplicates
        newNodes[i] = NodeEulerianCycle("Node $i")
    }

    // cast the nodes of the array to a not nullable type
    val resultingNodes: List<NodeEulerianCycle> = newNodes.map { i -> i!! }

    // for each node except the last node:
    // create outgoing edge to the next node and outgoing edge from this node to the current node
    for (nodeIndex in 0 .. resultingNodes.size - 2) {
        resultingNodes[nodeIndex].outgoingEdges += listOf(resultingNodes[nodeIndex + 1])
        resultingNodes[nodeIndex + 1].outgoingEdges += listOf(resultingNodes[nodeIndex])
    }

    return GraphEulerianCycle(resultingNodes.shuffled().toTypedArray())
}

/**
 *
 */
fun createEulerianGraphWithFourSubCycles(): GraphEulerianCycle {
    val firstNode: NodeEulerianCycle = NodeEulerianCycle("1")
    val secondNode: NodeEulerianCycle = NodeEulerianCycle("0")
    val thirdNode: NodeEulerianCycle = NodeEulerianCycle("3")
    val fourthNode: NodeEulerianCycle = NodeEulerianCycle("2")
    val fifthNode: NodeEulerianCycle = NodeEulerianCycle("4")
    val sixthNode: NodeEulerianCycle = NodeEulerianCycle("5")

    firstNode.outgoingEdges = listOf(secondNode, thirdNode, fourthNode, fifthNode, sixthNode)
    secondNode.outgoingEdges = listOf(firstNode, thirdNode, fourthNode, fifthNode, sixthNode)
    thirdNode.outgoingEdges = listOf(firstNode, secondNode, fourthNode, fifthNode, sixthNode)
    fourthNode.outgoingEdges = listOf(firstNode, secondNode, thirdNode, fifthNode, sixthNode)
    fifthNode.outgoingEdges = listOf(firstNode, secondNode, thirdNode, fourthNode, sixthNode)
    sixthNode.outgoingEdges = listOf(firstNode, secondNode, thirdNode, fourthNode, fifthNode)

    val nodeArr: Array<NodeEulerianCycle> = arrayOf(firstNode, secondNode, thirdNode, fourthNode,
        fifthNode, sixthNode)

    return GraphEulerianCycle(nodeArr)
}

/**
 * Returns the Graph of the 3-mers for Hamiltonian Cycle from the paper.
 */
fun createHamiltonianGraphFromThePaper(): GraphHamiltonianCycle {

    val firstNode: NodeHamiltonianCycle = NodeHamiltonianCycle("ATG")
    val secondNode: NodeHamiltonianCycle = NodeHamiltonianCycle("TGG")
    val thirdNode: NodeHamiltonianCycle = NodeHamiltonianCycle("GGC")
    val fourthNode: NodeHamiltonianCycle = NodeHamiltonianCycle("GCG")
    val fifthNode: NodeHamiltonianCycle = NodeHamiltonianCycle("CGT")
    val sixthNode: NodeHamiltonianCycle = NodeHamiltonianCycle("GTG")
    val seventhNode: NodeHamiltonianCycle = NodeHamiltonianCycle("TGC")
    val eighthNode: NodeHamiltonianCycle = NodeHamiltonianCycle("GCA")
    val ninthNode: NodeHamiltonianCycle = NodeHamiltonianCycle("CAA")
    val tenthNode: NodeHamiltonianCycle = NodeHamiltonianCycle("AAT")

    firstNode.outgoingEdges = listOf(secondNode, seventhNode)
    secondNode.outgoingEdges = listOf(thirdNode)
    thirdNode.outgoingEdges = listOf(fourthNode, eighthNode)
    fourthNode.outgoingEdges = listOf(fifthNode)
    fifthNode.outgoingEdges = listOf(sixthNode)
    sixthNode.outgoingEdges = listOf(seventhNode, secondNode)
    seventhNode.outgoingEdges = listOf(eighthNode, fourthNode)
    eighthNode.outgoingEdges = listOf(ninthNode)
    ninthNode.outgoingEdges = listOf(tenthNode)
    tenthNode.outgoingEdges = listOf(firstNode)

    val nodeArr: Array<NodeHamiltonianCycle> = arrayOf(firstNode, secondNode, thirdNode, fourthNode,
        fifthNode, sixthNode, seventhNode,
        eighthNode, ninthNode, tenthNode)
    nodeArr.shuffle()

    return GraphHamiltonianCycle(nodeArr)
}

/**
 * Returns the Graph of the 2-mers for Eulerian Cycle from the paper.
 */
fun createEulerianGraphFromThePaper(): GraphEulerianCycle {
    val firstNode: NodeEulerianCycle = NodeEulerianCycle("AT")
    val secondNode: NodeEulerianCycle = NodeEulerianCycle("TG")
    val thirdNode: NodeEulerianCycle = NodeEulerianCycle("GG")
    val fourthNode: NodeEulerianCycle = NodeEulerianCycle("GC")
    val fifthNode: NodeEulerianCycle = NodeEulerianCycle("CG")
    val sixthNode: NodeEulerianCycle = NodeEulerianCycle("GT")
    val seventhNode: NodeEulerianCycle = NodeEulerianCycle("CA")
    val eighthNode: NodeEulerianCycle = NodeEulerianCycle("AA")

    firstNode.outgoingEdges = listOf(secondNode)
    secondNode.outgoingEdges = listOf(thirdNode, fourthNode)
    thirdNode.outgoingEdges = listOf(fourthNode)
    fourthNode.outgoingEdges = listOf(fifthNode, seventhNode)
    fifthNode.outgoingEdges = listOf(sixthNode)
    sixthNode.outgoingEdges = listOf(secondNode)
    seventhNode.outgoingEdges = listOf(eighthNode)
    eighthNode.outgoingEdges = listOf(firstNode)

    val nodeArr: Array<NodeEulerianCycle> = arrayOf(firstNode, secondNode, thirdNode, fourthNode,
        fifthNode, sixthNode, seventhNode, eighthNode)

    return GraphEulerianCycle(nodeArr)
}

/**
 * Returns the Graph of the 2-mers for Eulerian Cycle from the paper.
 */
fun createEulerianGraphFromThePaper_inDifferentOrder(): GraphEulerianCycle {
    val firstNode: NodeEulerianCycle = NodeEulerianCycle("AT")
    val secondNode: NodeEulerianCycle = NodeEulerianCycle("TG")
    val thirdNode: NodeEulerianCycle = NodeEulerianCycle("GG")
    val fourthNode: NodeEulerianCycle = NodeEulerianCycle("GC")
    val fifthNode: NodeEulerianCycle = NodeEulerianCycle("CG")
    val sixthNode: NodeEulerianCycle = NodeEulerianCycle("GT")
    val seventhNode: NodeEulerianCycle = NodeEulerianCycle("CA")
    val eighthNode: NodeEulerianCycle = NodeEulerianCycle("AA")

    firstNode.outgoingEdges = listOf(secondNode)
    secondNode.outgoingEdges = listOf(thirdNode, fourthNode)
    thirdNode.outgoingEdges = listOf(fourthNode)
    fourthNode.outgoingEdges = listOf(fifthNode, seventhNode)
    fifthNode.outgoingEdges = listOf(sixthNode)
    sixthNode.outgoingEdges = listOf(secondNode)
    seventhNode.outgoingEdges = listOf(eighthNode)
    eighthNode.outgoingEdges = listOf(firstNode)

    val nodeArr: Array<NodeEulerianCycle> = arrayOf(thirdNode, fourthNode, fifthNode, sixthNode,
        secondNode, firstNode, seventhNode, eighthNode)

    return GraphEulerianCycle(nodeArr)
}


fun main(args: Array<String>) {

    println("Hello World!\n\n")


    // Hamiltonian Cycle
    println("-----------------------HAMILTONIAN-----------------------\n")

    // Example from the paper
    val hGraph1: GraphHamiltonianCycle = createHamiltonianGraphFromThePaper()
//    println("\n$hGraph1)
    for (i in 0..5) {
        hGraph1.nodes.shuffle()
        println("Hamiltonian Cycle: ${hGraph1.findHamiltonianCycle()}")
    }

    // examples of arbitrary size
    val size: Int = 6      // 10 needs few seconds
    val hGraph2: GraphHamiltonianCycle = createCompleteHamiltonianGraph(size)
//    println("\n\n$g")
    println("\nHamilton Cycle of a complete graph of size $size: ")
    println("  ${hGraph2.findHamiltonianCycle()}\n\n")


    // Eulerian Cycle
    println("------------------------EULERIAN------------------------\n")

    var eGraph1: GraphEulerianCycle

    // example from the paper
    eGraph1 = createEulerianGraphFromThePaper_inDifferentOrder()

    // random complete graph - max value about 65, stackoverflowerror for values above
    eGraph1 = createCompleteEulerianGraph(10)

    // concrete graph as example
    eGraph1 = createEulerianGraphWithFourSubCycles()
    // 1  0  3  2  4  5  4  2  5  2  3  4  3  5  3  0  2  0  4  0  5  0  1  3  1  2  1  4  1  5  1
    //

    // random doubly linked list graph - max value about 3000, maybe 3500, errors for values above
//    eGraph1 = createDoublyLinkedListEulerianGraph(2000)

    // print graph
    println("$eGraph1")

    // print Eulerian Cycle
    println("EULERIAN CYCLE")  // todo delete afterward
    println("RESULT:     ${eGraph1.findEulerianCycle()}")

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
