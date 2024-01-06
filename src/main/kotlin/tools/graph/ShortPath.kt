package tools.graph

fun <Node : Any> shortPath(
    start: Node,
    end: Node,
    cost: (origin: Node, destination: Node) -> Double = { _, _ -> 1.0 },
    estimatedEndCost: (Node) -> Double = { 0.0 }, // A*
    neighbors: (Node) -> List<Node>
) = shortPath(start, isEnd = { this == end }, cost, estimatedEndCost, neighbors)

fun <Node : Any> shortPath(
    start: Node,
    isEnd: Node.() -> Boolean,
    cost: (origin: Node, destination: Node) -> Double = { _, _ -> 1.0 },
    toEndMinimalCost: (Node) -> Double = { 0.0 }, // A*
    neighbors: Node.() -> List<Node>
): List<Node> {
    val spStart = ShortPathNode(start, 0.0, toEndMinimalCost(start), true)
    val spNodes = mutableMapOf(start to spStart)
    val todo = mutableListOf(spStart)
    while (true) {
        val currentSPNode = todo.removeFirstOrNull() ?: return emptyList()
        currentSPNode.todo = false
        if (currentSPNode.node.isEnd())
            return generateSequence(currentSPNode) { it.predecessor }.map { it.node }.toList().reversed()
        neighbors(currentSPNode.node).forEach { nextNode ->
            val newFromStartCost = currentSPNode.fromStartCost + cost(currentSPNode.node, nextNode)
            val nextSPNode = spNodes[nextNode]
            when {
                nextSPNode == null -> {
                    val spNode = ShortPathNode(
                        nextNode,
                        newFromStartCost,
                        newFromStartCost + toEndMinimalCost(nextNode),
                        true
                    )
                    spNode.predecessor = currentSPNode
                    spNodes[nextNode] = spNode
                    todo.add(-todo.binarySearch(spNode) - 1, spNode)
                }

                newFromStartCost < nextSPNode.fromStartCost -> {
                    var toIndex = todo.size
                    if (nextSPNode.todo) {
                        toIndex = todo.binarySearch(nextSPNode)
                        todo.removeAt(toIndex)
                    }
                    nextSPNode.predecessor = currentSPNode
                    nextSPNode.minimalCost -= nextSPNode.fromStartCost - newFromStartCost
                    nextSPNode.fromStartCost = newFromStartCost
                    nextSPNode.todo = true
                    todo.add(-todo.binarySearch(nextSPNode, toIndex = toIndex) - 1, nextSPNode)
                }
            }
        }
    }
}

private data class ShortPathNode<Node : Any>(
    val node: Node,
    var fromStartCost: Double,
    var minimalCost: Double,
    var todo: Boolean
) : Comparable<ShortPathNode<Node>> {
    var predecessor: ShortPathNode<Node>? = null

    override fun compareTo(other: ShortPathNode<Node>): Int {
        val compare = minimalCost.compareTo(other.minimalCost)
        return if (compare != 0) compare else id.compareTo(other.id)
    }

    private val id = nextId++

    companion object {
        private var nextId = Int.MIN_VALUE
    }
}
