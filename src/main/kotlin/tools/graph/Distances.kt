package tools.graph

/**
 * Computes all distance from a vertex.
 *
 * @param size the number of vertices.
 * @param start the index of the origin vertex.
 * @param neighbors the neighbors of a vertex with their costs.
 */
fun distancesFromOneToAll(size: Int, start: Int, neighbors: (Int) -> Map<Int, Double>): DoubleArray {
    val distances = DoubleArray(size) { Double.POSITIVE_INFINITY }
    val comparator = Comparator<Int> { a, b -> distances[a].compareTo(distances[b]) }.thenBy { it }
    distances[start] = 0.0
    val todo = mutableListOf(start)
    while (true) {
        val currentNode = todo.removeFirstOrNull() ?: return distances
        val currentDistance = distances[currentNode]
        neighbors(currentNode).forEach { (nextNode, cost) ->
            val d = currentDistance + cost
            if (d < distances[nextNode]) {
                val toIndex = todo.binarySearch(nextNode, comparator).let {
                    if (it < 0) return@let todo.size
                    todo.removeAt(it)
                    it
                }
                distances[nextNode] = d
                todo.add(-todo.binarySearch(nextNode, comparator, toIndex = toIndex) - 1, nextNode)
            }
        }
    }
}
