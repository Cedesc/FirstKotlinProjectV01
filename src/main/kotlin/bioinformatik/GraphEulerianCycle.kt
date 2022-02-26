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
    fun findEulerianCycle(): String {

        // Eulers theorem implies that an Eulerian Cycle exists exactly when the graph is balanced.
        // So, if the graph is not balanced, there is no Eulerian Cycle.
        if (! isBalanced()) {
            return "The Graph is not balanced, so there is no Eulerian Cycle"
        }

        else {
            // find all Eulerian subcycles
            val subCyclesAsString: List<List<String>> = findFullEulerianSubCycles()
            println("SubCycles: $subCyclesAsString")  // todo delete afterward

            // merge all Eulerian Subcycles to one Eulerian Cycle
            val eulerianCycle: List<String> = mergeEulerianSubCycles(subCyclesAsString)
            println("CycleAsList: $eulerianCycle")  // todo delete afterward

            // convert the complete Eulerian Cycle to a String
            return convertCyclesFromListToString(eulerianCycle)
        }
    }

    /**
     *
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
     *
     */
    private fun convertCyclesFromStringToList(subCycles: List<String>): List<List<String>> {
        return subCycles.map { i -> i.split("  ") }
    }

    /**
     *
     */
    private fun convertCyclesFromListToString(eulerianCycle: List<String>): String {
        var result: String = ""
        for (cycle: String in eulerianCycle.slice(0 .. eulerianCycle.size - 2)) {
            result += "$cycle  "
        }
        return result + eulerianCycle[eulerianCycle.size - 1]
    }

    /**
     *
     */
    private fun mergeEulerianSubCycles(subCycles: List<List<String>>): List<String> {

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

                    println("hahahaha:   $newInput")

                    return mergeEulerianSubCycles(newInput)

                }
            }
        }

        println("Error! Not all Cycles could be connected!")
        return emptyList()
    }

    // todo make fun private
    /**
     * Returns all Eulerian SubCycles. Not all possible or all possible combinations, but all possible in one graph
     * without overlapping edges. Needed to construct an Eulerian Cycle.
     */
    fun findFullEulerianSubCycles(): List<List<String>> {

        val results: MutableList<List<String>> = mutableListOf()

        outer@while (true) {

            inner@for (node in nodes) {
                // search for a node that has an outgoing edge
                if (node.outgoingEdges.isNotEmpty()) {
                    // if a node with an outgoing edge was found, find an Eulerian SubCycle containing it...
//                    results += findOneEulerianSubCycle(node) todo delete afterward
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
     * Returns an Eulerian SubCycle and delete all edges of the graph that are in this Cycle.
     */
    private fun findOneEulerianSubCycle(currentNode: NodeEulerianCycle): List<String> {

        if (currentNode.outgoingEdges.isEmpty()) {

            // if the currentNode has no further outgoing edges, terminate
            return listOf(currentNode.k_mer)

        } else {

            // cache the next node and delete the edge (outgoing and incoming)
            val nextNode: NodeEulerianCycle = currentNode.outgoingEdges[0]
            currentNode.outgoingEdges -= currentNode.outgoingEdges[0]
            nextNode.incomingEdges -= currentNode

            return listOf(currentNode.k_mer) + findOneEulerianSubCycle(nextNode)
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