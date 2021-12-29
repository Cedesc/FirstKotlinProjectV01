package bioinformatik

/**
 * Represents a node for the graph built to find an Eulerian Cycle.
 *
 * Every Node stores its outgoing edges and his k-mer.
 */
class NodeEulerianCycle(val k_mer: String, var outgoingEdges: List<NodeEulerianCycle> = listOf()) {

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
        for (adjacentNode: NodeEulerianCycle in outgoingEdges) {
            stringOfOutgoingEdges += adjacentNode.k_mer + " "
        }
        return stringOfOutgoingEdges
    }
}
// todo same code as NodeHamiltonianCycle. Merge the two together?