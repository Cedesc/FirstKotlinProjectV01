package bioinformatik

/**
 * Represents the graph constructed to find a Hamiltonian Cycle.
 *
 * A graph stores its contained nodes.
 */
class GraphHamiltonianCycle(val nodes: Array<NodeHamiltonianCycle>) {

    init {
        if (! allEdgesPointsToANode()) {
            println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            println("WARNING! NOT ALL EDGES POINT TO A NODE OF THE PASSED NODES!")
            println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        }
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

        // if all nodes are included and the String ends with the first node, it has to be a Hamiltonian Cycle
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