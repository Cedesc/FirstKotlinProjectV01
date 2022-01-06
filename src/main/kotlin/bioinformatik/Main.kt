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
    nodeArr.shuffle()

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

    // Examples of arbitrary size
    val size: Int = 6
    val hGraph2: GraphHamiltonianCycle = createCompleteHamiltonianGraph(size)
//    println("\n\n$g")
    println("\nHamilton Cycle of a complete graph of size $size: ")
    println("  ${hGraph2.findHamiltonianCycle()}\n\n")


    // Eulerian Cycle
    println("------------------------EULERIAN------------------------\n")

    // Example from the paper
    val eGraph1: GraphEulerianCycle = createEulerianGraphFromThePaper()
    println("$eGraph1")
//    for (i in 0..5) {
//        eGraph1.nodes.shuffle()
//        println("Eulerian Cycle: ${eGraph1.findEulerianCycle()}")
//    }
    println("Is eGraph1 balanced? (Should be true)   ${eGraph1.isBalanced()}")
    println("${eGraph1.findFullEulerianSubCycles()}")
    // [GG  GC  CG  GT  TG  GG, AT  TG  GC  CA  AA  AT]
    // [CA  AA  AT  TG  GG  GC  CG  GT  TG  GC  CA]
    // [GT  TG  GG  GC  CG  GT, CA  AA  AT  TG  GC  CA]

}

