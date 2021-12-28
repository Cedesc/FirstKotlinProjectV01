package bioinformatik

/**
 * Returns a complete graph of arbitrary size.
 */
fun createCompleteHamiltonianGraph(size: Int): GraphHamiltonianCycle {

    // create Array filled with null
    val newNodes: Array<NodeHamiltonianCycle?> = Array(size + 1) {null}

    // fill the array with nodes
    for (i in 0..size) {
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
 * Returns the Graph of the 3-mers from the paper.
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


fun main(args: Array<String>) {

    println("Hello World!\n")

    // Example of the paper

    val graph1: GraphHamiltonianCycle = createHamiltonianGraphFromThePaper()
//    println("\n" + graph)

    for (i in 0..5) {
        graph1.nodes.shuffle()
        println(graph1.findHamiltonianCycle())
    }


    // Examples of arbitrary size

    val size: Int = 8
    val graph2: GraphHamiltonianCycle = createCompleteHamiltonianGraph(size)
//    println("\n\n$g")
    println("\nHamilton Cycle of a complete graph of size $size: ")
    println("  ${graph2.findHamiltonianCycle()}")

}

