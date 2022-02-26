package bioinformatik

/**
 * Represents the graph constructed to find a Hamiltonian Cycle.
 *
 * A graph stores its contained nodes.
 */
class GraphEulerianCycle(private val nodes: Array<NodeEulerianCycle>) {

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
     * Returns true if it implies for all nodes that the number of outgoing edges is equal to the number of incoming
     * edges.
     */
    private fun isBalanced(): Boolean {
        for (node: NodeEulerianCycle in nodes) {
            if (node.outgoingEdges.size != node.incomingEdges.size)
                return false
        }
        return true
    }

    /**
     * Returns an Eulerian Cycle as a String of the k-mers or null if no Eulerian Cycle exists.
     */
    fun findEulerianCycle(printSteps: Boolean = false): String? {

        // Eulers theorem implies that an Eulerian Cycle exists exactly when the graph is balanced
        // so, if the graph is not balanced, there is no Eulerian Cycle.
        if (! isBalanced()) {
            println("The Graph is not balanced, so there is no Eulerian Cycle")
            return null
        }

        else {
            // find all Eulerian Sub-Cycles
            val subCycles: List<List<String>> = findFullEulerianSubCycles()
            if (printSteps)
                println("Sub-Cycles: $subCycles")

            // merge all Eulerian Sub-Cycles to one Eulerian Cycle
            val eulerianCycle: List<String> = mergeEulerianSubCycles(subCycles, printSteps)

            // convert the complete Eulerian Cycle to a String
            return convertCyclesFromListToString(eulerianCycle)
        }
    }

    /**
     * Returns all Eulerian SubCycles. Not all possible or all possible combinations, but all possible in one graph
     * without overlapping edges.
     *
     * Needed to construct an Eulerian Cycle.
     */
    private fun findFullEulerianSubCycles(): List<List<String>> {

        val results: MutableList<List<String>> = mutableListOf()

        outer@while (true) {

            inner@for (node in nodes) {
                // search for a node that has an outgoing edge
                if (node.outgoingEdges.isNotEmpty()) {
                    // if a node with an outgoing edge was found, find an Eulerian SubCycle containing it...
                    results.add(findOneEulerianSubCycle(node))
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
     * Returns an Eulerian Sub-Cycle and delete all edges of the graph that are in this Cycle.
     */
    private fun findOneEulerianSubCycle(currentNode: NodeEulerianCycle): List<String> {

        return if (currentNode.outgoingEdges.isEmpty()) {

            // if the currentNode has no further outgoing edges, terminate
            listOf(currentNode.k_mer)

        } else {

            // cache the next node and delete the edge (outgoing and incoming)
            val nextNode: NodeEulerianCycle = currentNode.outgoingEdges[0]
            currentNode.outgoingEdges -= currentNode.outgoingEdges[0]
            nextNode.incomingEdges -= currentNode

            listOf(currentNode.k_mer) + findOneEulerianSubCycle(nextNode)
        }
    }

    /**
     * Gets the sub-cycles as input and returns one list of the merged k-mers of the sub-cycles.
     */
    private fun mergeEulerianSubCycles(subCycles: List<List<String>>, printSteps: Boolean = false): List<String> {

        if (subCycles.isEmpty()) {
            println("ERROR! No SubCycles found!")
            return emptyList()
        }

        else if (subCycles.size == 1)
            return subCycles[0]

        // else: subCycles.size >= 2

        // find a shared node of the first subcycle and another subcycle
        for (otherCycleIndex: Int in subCycles.indices - 0) {

            for (node: String in subCycles[0]) {
                if (node in subCycles[otherCycleIndex]) {

                    // call the function recursively with the merged cycle as the first element and with the other
                    // unaffected subcycles, but without the cycles that have been merged together
                    val newInput: List<List<String>> =
                        // merged subCycle
                        listOf(mergeTwoCycles(subCycles[0], subCycles[otherCycleIndex], node)) +
                                // exclude the first subcycle
                                subCycles.slice(1 until otherCycleIndex) +
                                // exclude the other found subcycle
                                subCycles.slice(otherCycleIndex + 1 until subCycles.size)  //

                    if (printSteps)
                        println("Merging:    $newInput")

                    return mergeEulerianSubCycles(newInput, printSteps)

                }
            }
        }

        println("Error! Not all Cycles could be connected!")
        return emptyList()
    }

    /**
     * Merges exactly two cycles with a given common node.
     */
    private fun mergeTwoCycles(cycle1: List<String>, cycle2: List<String>, commonNode: String): List<String> {

        val indexInFirstCycle: Int = cycle1.indexOf(commonNode)
        val indexInSecondCycle: Int = cycle2.indexOf(commonNode)

        // Check if the commonNode is in both cycles included
        if (indexInFirstCycle == -1) {
            println("Error! The 'commonNode' isn't in the first cycle!!")
            return emptyList()
        }
        if (indexInSecondCycle == -1) {
            println("Error! The 'commonNode' isn't in the second cycle!!")
            return emptyList()
        }

        // bring the nodes of the second list in the correct order to insert them all at once
        val newOrderOfSecondCycle: List<String> = cycle2.slice(indexInSecondCycle until cycle2.size) +
                cycle2.slice(1..indexInSecondCycle)

        // insert all nodes of the second list
        return cycle1.slice(0 until indexInFirstCycle) +
                newOrderOfSecondCycle +
                cycle1.slice(indexInFirstCycle + 1 until cycle1.size)
    }

    /**
     * Converts the List of k-mers to a single string.
     */
    private fun convertCyclesFromListToString(eulerianCycle: List<String>): String {
        var result = ""
        for (cycle: String in eulerianCycle.slice(0 .. eulerianCycle.size - 2)) {
            result += "$cycle  "
        }
        return result + eulerianCycle[eulerianCycle.size - 1]
    }

    /**
     * Overrides toString() to return k-mer and outgoing edges of all included nodes.
     */
    override fun toString(): String {
        var stringOutput = ""
        for (node: NodeEulerianCycle in nodes) {
            stringOutput += "  $node"
        }
        return "Graph: \n$stringOutput"
    }

}
