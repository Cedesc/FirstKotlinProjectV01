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
     * Returns true if it implies for all nodes that the number of outgoing edges is equal to the number of incoming
     * edges.
     */
    fun isBalanced(): Boolean {
        for (node: NodeEulerianCycle in nodes) {
            if (node.outgoingEdges.size != node.incomingEdges.size)
                return false
        }
        return true
    }

    /**
     *
     */
    fun findEulerianCycle(): String? {

        // Eulers theorem implies that an Eulerian Cycle exists exactly when the graph is balanced.
        // So, if the graph is not balanced, there is no Eulerian Cycle.
        if (! isBalanced())
            return null

        else {
            //todo
        }

        return null
    }

    /**
     *
     */
    fun mergeEulerianSubCycles(subCycles: List<String>): String {

        if (subCycles.isEmpty()) {
            println("ERROR")
            return ""
        }
        else if (subCycles.size == 1)
            return subCycles[0]
        if (subCycles.size >= 2) {
            // todo
        }
        // [GG  GC  CG  GT  TG  GG, AT  TG  GC  CA  AA  AT]
        return ""
    }

    // todo make fun private
    /**
     * Returns all Eulerian SubCycles. Not all possible or all possible combinations, but all possible in one graph
     * without overlapping edges. Needed to construct an Eulerian Cycle.
     */
    fun findFullEulerianSubCycles(): List<String> {

        val results: MutableList<String> = mutableListOf()

        outer@while (true) {

            inner@for (node in nodes) {
                // search for a node that has an outgoing edge
                if (node.outgoingEdges.isNotEmpty()) {
                    // if a node with an outgoing edge was found, find an Eulerian SubCycle containing it...
                    results += findOneEulerianSubCycle(node)
                    // ...and search for a node that still has an outgoing edge (repeat the for loop)
                    continue@outer
                }
            }
            // if no node has an outgoing edge, terminate
            break@outer
        }

        return results
    }

    /**
     * Returns an Eulerian SubCycle and delete all edges of the graph that are in this Cycle.
     */
    private fun findOneEulerianSubCycle(currentNode: NodeEulerianCycle): String {

        if (currentNode.outgoingEdges.isEmpty()) {

            // if the currentNode has no further outgoing edges, terminate
            return currentNode.k_mer

        } else {

            // cache the next node and delete the edge (outgoing and incoming)
            val nextNode: NodeEulerianCycle = currentNode.outgoingEdges[0]
            currentNode.outgoingEdges -= currentNode.outgoingEdges[0]
            nextNode.incomingEdges -= currentNode

            return currentNode.k_mer + "  " + findOneEulerianSubCycle(nextNode)
        }
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