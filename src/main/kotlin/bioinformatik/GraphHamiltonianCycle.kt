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
     * Returns a Hamiltonian Cycle as a String of the k-mers or null if no Hamiltonian Cycle exist.
     */
    fun findHamiltonianCycle(): String? {

        val potentialHamiltonianCycle: String = findPotentialHamiltonianCycle()

        if (potentialHamiltonianCycle.length == numberOfNodes() * 4 + 3) // todo generalize for k-mers instead of 3-mers
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
            if (adjacentNode !in visitedNodes) {
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
class NodeHamiltonianCycle(val k_mer: String, var outgoingEdges: Array<NodeHamiltonianCycle> = arrayOf()) {

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



fun main(args: Array<String>) {

    println("Hello World!\n")

    // Example of the paper

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

    firstNode.outgoingEdges = arrayOf(secondNode, seventhNode)
    secondNode.outgoingEdges = arrayOf(thirdNode)
    thirdNode.outgoingEdges = arrayOf(fourthNode, eighthNode)
    fourthNode.outgoingEdges = arrayOf(fifthNode)
    fifthNode.outgoingEdges = arrayOf(sixthNode)
    sixthNode.outgoingEdges = arrayOf(seventhNode, secondNode)
    seventhNode.outgoingEdges = arrayOf(eighthNode, fourthNode)
    eighthNode.outgoingEdges = arrayOf(ninthNode)
    ninthNode.outgoingEdges = arrayOf(tenthNode)
    tenthNode.outgoingEdges = arrayOf(firstNode)


    val nodeArr: Array<NodeHamiltonianCycle> = arrayOf(firstNode, secondNode, thirdNode, fourthNode,
        fifthNode, sixthNode, seventhNode,
        eighthNode, ninthNode, tenthNode)
    nodeArr.shuffle()


    val graph: GraphHamiltonianCycle = GraphHamiltonianCycle(nodeArr)
    println("\n" + graph)


    println(graph.findHamiltonianCycle())
    graph.nodes.shuffle()
    println(graph.findHamiltonianCycle())
    graph.nodes.shuffle()
    println(graph.findHamiltonianCycle())
    graph.nodes.shuffle()
    println(graph.findHamiltonianCycle())
    graph.nodes.shuffle()
    println(graph.findHamiltonianCycle())


    // TGC GCG CGT GTG TGG GGC GCA CAA AAT ATG TGC
    // GGC GCA CAA AAT ATG TGC GCG CGT GTG TGG GGC
    // GCG CGT GTG TGG GGC GCA CAA AAT ATG TGC GCG



    // Bigger examples todo put this following stuff in extra function

    val size: Int = 11

    val moreNodes: Array<NodeHamiltonianCycle?> = Array(size + 1) {null}
// val tenthNode: NodeHamiltonianCycle = NodeHamiltonianCycle("AAT")
//
//    firstNode.outgoingEdges = arrayOf(secondNode, seventhNode)

    for (i in 0..size) {
        moreNodes[i] = NodeHamiltonianCycle("AAA")
    }

    moreNodes.map { i -> i!! }
    val reallyMoreNodes: Array<NodeHamiltonianCycle> = moreNodes.map { i -> i!! }.toTypedArray()

    for (node in moreNodes) {
        node!!.outgoingEdges = Array(size) {i -> moreNodes[i]!!}
    }

    for (i in moreNodes) {
        println(i)
    }

    val graph2: GraphHamiltonianCycle = GraphHamiltonianCycle(reallyMoreNodes)
    graph2.findHamiltonianCycle()

}


/*
Input
    k-mers: Array<String>
Output
    Superstring: String

Hamiltonian Cycle
    (0. build 3-mers)
    1. build graph -- CHECK
    2. find hamiltonian cycle  -- CHECK
todo automatically calculation of the edges

Eulerian Cycle
    (0. build 3-mers)
    1. build 2-mers out of 3-mers
    2. build graph
    3. find eulerian cycle
 */
