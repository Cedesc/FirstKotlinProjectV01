package cyclesEulerianAndHamiltonian

/**
 * Returns the Graph of the 2-mers for Eulerian Cycle from the paper.
 */
fun createEulerianGraphFromThePaper(): GraphEulerianCycle {
    val firstNode = NodeEulerianCycle("AT")
    val secondNode = NodeEulerianCycle("TG")
    val thirdNode = NodeEulerianCycle("GG")
    val fourthNode = NodeEulerianCycle("GC")
    val fifthNode = NodeEulerianCycle("CG")
    val sixthNode = NodeEulerianCycle("GT")
    val seventhNode = NodeEulerianCycle("CA")
    val eighthNode = NodeEulerianCycle("AA")

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
 * Returns the graph of the 2-mers for Eulerian Cycle from the paper in a different order.
 *
 * This order will lead to two instead of one found sub-cycle.
 */
fun createEulerianGraphFromThePaperInDifferentOrder(): GraphEulerianCycle {
    val firstNode = NodeEulerianCycle("AT")
    val secondNode = NodeEulerianCycle("TG")
    val thirdNode = NodeEulerianCycle("GG")
    val fourthNode = NodeEulerianCycle("GC")
    val fifthNode = NodeEulerianCycle("CG")
    val sixthNode = NodeEulerianCycle("GT")
    val seventhNode = NodeEulerianCycle("CA")
    val eighthNode = NodeEulerianCycle("AA")

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

/**
 * Returns a graph in which four sub-cycles are found.
 */
fun createEulerianGraphWithFourSubCycles(): GraphEulerianCycle {
    val firstNode = NodeEulerianCycle("1")
    val secondNode = NodeEulerianCycle("0")
    val thirdNode = NodeEulerianCycle("3")
    val fourthNode = NodeEulerianCycle("2")
    val fifthNode = NodeEulerianCycle("4")
    val sixthNode = NodeEulerianCycle("5")

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
 * Returns a complete graph of arbitrary size.
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
 * Returns a graph in form of a doubly linked list.
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
