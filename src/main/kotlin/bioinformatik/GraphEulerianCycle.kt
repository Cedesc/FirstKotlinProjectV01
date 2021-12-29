package bioinformatik

/**
 * Represents the graph constructed to find a Hamiltonian Cycle.
 *
 * A graph stores its contained nodes.
 */
class GraphEulerianCycle(val nodes: Array<NodeEulerianCycle>) {

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
        for (node: NodeEulerianCycle in nodes) {
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
     * Returns true if it implies for all nodes that the number of outgoing edges is equal to the number of incoming edges
     */
    fun isBalanced(): Boolean {
        // todo
        return false
    }

    /**
     *
     */
    fun findEulerianCycle(): String? {
        //todo
        return null
    }

    /**
     * Overrides toString() to return k-mer and outgoing edges of all included nodes.
     */
    override fun toString(): String {
        var stringOutput: String = ""
        for (node: NodeEulerianCycle in nodes) {
            stringOutput += "  $node"
        }
        return "Graph: \n$stringOutput"
    }

}