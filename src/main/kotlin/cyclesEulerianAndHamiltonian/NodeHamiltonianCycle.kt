package cyclesEulerianAndHamiltonian

/**
 * Represents a node for the graph built to find a Hamiltonian Cycle.
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
        var stringOfOutgoingEdges = ""
        for (adjacentNode: NodeHamiltonianCycle in outgoingEdges) {
            stringOfOutgoingEdges += adjacentNode.k_mer + " "
        }
        return stringOfOutgoingEdges
    }
}
