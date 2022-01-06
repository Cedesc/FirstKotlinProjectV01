package bioinformatik

/**
 * Represents a node for the graph built to find an Eulerian Cycle.
 *
 * Every Node stores its outgoing edges and his k-mer.
 */
class NodeEulerianCycle(val k_mer: String, outgoingEdges: List<NodeEulerianCycle> = listOf()) {

    var incomingEdges: List<NodeEulerianCycle> = listOf()

    // for every outgoing edge, there is an incoming edge to the other node
    var outgoingEdges: List<NodeEulerianCycle> = outgoingEdges
        set(value) {
            field = value
            for (node: NodeEulerianCycle in outgoingEdges) {
                node.incomingEdges += this
            }
        }

    /**
     * Overrides toString() to return the k-mer and the outgoing edges of the node.
     */
    override fun toString(): String {

        return "k-mer: $k_mer \n" +
                "    Outgoing Edges: ${outgoingEdgesToString()}\n" +
                "    Incoming Edges: ${incomingEdgesToString()}\n"
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

    /**
     * Returns a string of all k-mers of the incoming edges of this node.
     */
    private fun incomingEdgesToString(): String {
        var stringOfIncomingEdges: String = ""
        for (adjacentNode: NodeEulerianCycle in incomingEdges) {
            stringOfIncomingEdges += adjacentNode.k_mer + " "
        }
        return stringOfIncomingEdges
    }
}
