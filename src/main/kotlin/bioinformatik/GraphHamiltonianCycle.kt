package bioinformatik


/**
 * Represents the graph constructed to find a Hamiltonian Cycle.
 *
 * A graph stores its contained nodes.
 */
class GraphHamiltonianCycle(val nodes: Array<NodeHamiltonianCycle>) {

    init {
        if (! allEdgesPointsToANode())
            println("WARNING! NOT ALL EDGES POINT TO A NODE OF THE PASSED NODES!")
    }

    /**
     * Returns whether all edges points to a node in the graph or not.
     */
    private fun allEdgesPointsToANode(): Boolean {
        for (node: NodeHamiltonianCycle in nodes) {
            if (! node.outgoingEdges.all { nodes.contains(it) })
                return false
        }
        return true
    }

    /**
     * Returns the number of contained nodes.
     */
    private fun numberOfNodes(): Int {
        return nodes.size
    }

    /**
     * Returns a Hamiltonian Cycle as a String of the k-mers or null if no Hamiltonian Cycle exists.
     */
    fun findHamiltonianCycle(): String? {

        val potentialHamiltonianCycle: String = findPotentialHamiltonianCycle()

//        val k: Int = 6  // todo generalize for k-mers instead of 3-mers
//        if (potentialHamiltonianCycle.length == numberOfNodes() * k + k-1)
//            return potentialHamiltonianCycle

        if (nodes.all { potentialHamiltonianCycle.contains(it.k_mer) }
            && potentialHamiltonianCycle.endsWith(nodes[0].k_mer))
            return potentialHamiltonianCycle

        return null
    }

    /**
     * Returns a Hamiltonian Cycle as a String of the k-mers if one exists, otherwise the return value isn't meaningful.
     */
    private fun findPotentialHamiltonianCycle(currentNode: NodeHamiltonianCycle = nodes[0],
                                      visitedNodes: Array<NodeHamiltonianCycle> = arrayOf(),
                                      origin: NodeHamiltonianCycle = nodes[0]): String {

        // stop if the current node is the origin node and its not the first function call
        if (currentNode == origin && visitedNodes.isNotEmpty())
            return currentNode.k_mer

        // check if all adjacent nodes are marked as visited
        if (currentNode.outgoingEdges.all { visitedNodes.contains(it) })
            return ""

        val cycleCandidates: MutableList<String> = mutableListOf()

        // for all adjacent not visited nodes calculate all potential cycles recursively and add them to cycleCandidates
        for (adjacentNode: NodeHamiltonianCycle in currentNode.outgoingEdges) {
            if (adjacentNode !in visitedNodes && adjacentNode != currentNode) {
                cycleCandidates.add(
                    currentNode.k_mer + " " + findPotentialHamiltonianCycle(adjacentNode,
                                                                            visitedNodes + adjacentNode,
                                                                            origin))
            }
        }

        // return the longest String of the cycleCandidates
        // this works correct because no node is visited twice and all k-mers have the same length
        return cycleCandidates.fold("") { c1, c2 -> if (c1.length > c2.length) c1 else c2}
    }

    /**
     * Overrides toString() to return k-mer and outgoing edges of all included nodes.
     */
    override fun toString(): String {
        var stringOutput: String = ""
        for (node: NodeHamiltonianCycle in nodes) {
            stringOutput += "  $node"
        }
        return "Graph: \n$stringOutput"
    }

}

/**
 * Represents a node for the graph built to find a Hamiltonian Circle.
 *
 * Every Node stores its outgoing edges and his k-mer.
 */
class NodeHamiltonianCycle(val k_mer: String, var outgoingEdges: List<NodeHamiltonianCycle> = listOf()) {

    /**
     * Overrides toString() to return the k-mer and the outgoing edges of the node.
     */
    override fun toString(): String {

        return "k-mer: $k_mer \n" +
                "    Outgoing Edges: ${outgoingEdgesToString()}\n"
    }

    /**
     * Returns a string of all k-mers of the outgoing edges of this node.
     */
    private fun outgoingEdgesToString(): String {
        var stringOfOutgoingEdges: String = ""
        for (adjacentNode: NodeHamiltonianCycle in outgoingEdges) {
            stringOfOutgoingEdges += adjacentNode.k_mer + " "
        }
        return stringOfOutgoingEdges
    }
}


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

    val size: Int = 10
    val graph2: GraphHamiltonianCycle = createCompleteHamiltonianGraph(size)
//    println("\n\n$g")
    println("\nHamilton Cycle of a complete graph of size $size: ")
    println("  ${graph2.findHamiltonianCycle()}")

}

