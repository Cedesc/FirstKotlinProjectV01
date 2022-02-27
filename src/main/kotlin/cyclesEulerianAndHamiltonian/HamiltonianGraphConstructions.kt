package cyclesEulerianAndHamiltonian

/**
 * Returns the Graph of the 3-mers for Hamiltonian Cycle from the paper.
 */
fun createHamiltonianGraphFromThePaper(): GraphHamiltonianCycle {

    val firstNode = NodeHamiltonianCycle("ATG")
    val secondNode = NodeHamiltonianCycle("TGG")
    val thirdNode = NodeHamiltonianCycle("GGC")
    val fourthNode = NodeHamiltonianCycle("GCG")
    val fifthNode = NodeHamiltonianCycle("CGT")
    val sixthNode = NodeHamiltonianCycle("GTG")
    val seventhNode = NodeHamiltonianCycle("TGC")
    val eighthNode = NodeHamiltonianCycle("GCA")
    val ninthNode = NodeHamiltonianCycle("CAA")
    val tenthNode = NodeHamiltonianCycle("AAT")

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
 * Returns a graph in which each node has three outgoing edges to different nodes that also have an outgoing edge
 * to the respective node.
 */
fun createSampleHamiltonianGraph(size: Int): GraphHamiltonianCycle {

    // create Array filled with null
    val newNodes: Array<NodeHamiltonianCycle?> = Array(size) {null}

    // fill the array with nodes
    for (i in 0 until size) {
        // for this task, it's not important how long the k-mers are or if there are duplicates
        newNodes[i] = NodeHamiltonianCycle("Node $i")
    }

    // cast the nodes of the array to a not nullable type
    val resultingNodes: List<NodeHamiltonianCycle> = newNodes.map { i -> i!! }

    // for each node except the last node:
    // create outgoing edge to the next node and outgoing edge from this node to the current node
    for (nodeIndex in resultingNodes.indices) {
        resultingNodes[nodeIndex].outgoingEdges += listOf(resultingNodes[(nodeIndex + 1) % (resultingNodes.size)])
        resultingNodes[nodeIndex].outgoingEdges += listOf(resultingNodes[(nodeIndex + 2) % (resultingNodes.size)])
        resultingNodes[(nodeIndex + 1) % (resultingNodes.size)].outgoingEdges += listOf(resultingNodes[nodeIndex])
        resultingNodes[(nodeIndex + 2) % (resultingNodes.size)].outgoingEdges += listOf(resultingNodes[nodeIndex])
    }

    return GraphHamiltonianCycle(resultingNodes.shuffled().toTypedArray())
}
