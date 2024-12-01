package tools.graph

/**
 * A Edmonds-Karp algorithm that finding the shortest paths with maximizes flow.
 * https://en.wikipedia.org/wiki/Edmonds-Karp_algorithm
 *
 * @property size the number of vertices.
 * @property edges the edges of graph.
 */
data class MaxFlow(val size: Int, val edges: List<Edge>) {
    class Edge(val source: Int, val destination: Int, val capacity: Int, var flow: Int = 0) {
        internal lateinit var rev: Edge
    }

    /**
     * Computes the max flow.
     *
     * @param source the index of source.
     * @property sink the index of sink.
     */
    fun maxFlow(source: Int, sink: Int): Int {
        var maxFlow = 0
        neighbors.forEach { n -> n.forEach { it.flow = 0 } }
        val todo = mutableListOf<Int>()
        val parent = arrayOfNulls<Edge>(size)
        while (true) {
            todo.add(source)
            while (true) {
                val current = todo.removeFirstOrNull() ?: break
                for (edge in neighbors[current]) {
                    if (parent[edge.destination] == null && edge.destination != source && edge.flow < edge.capacity) {
                        parent[edge.destination] = edge
                        todo.add(edge.destination)
                    }
                }
            }
            if (parent[sink] == null) break
            var pushFlow = Int.MAX_VALUE
            parent.backward(sink) { pushFlow = pushFlow.coerceAtMost(it.capacity - it.flow) }
            parent.backward(sink) {
                it.flow += pushFlow
                it.rev.flow -= pushFlow
            }
            maxFlow += pushFlow
            todo.clear()
            parent.fill(null)
        }
        return maxFlow
    }

    private val neighbors = Array(size) { mutableListOf<Edge>() }

    init {
        for (e in edges) {
            val n = neighbors[e.source]
            n.add(-n.binarySearch(e.destination) - 1, e)
        }
        // add reverse edges (create it if needed)
        for (e in edges) {
            val n = neighbors[e.destination]
            val index = n.binarySearch(e.source)
            val r = if (index < 0) Edge(e.destination, e.source, 0).also { n.add(-index - 1, it) } else n[index]
            e.rev = r
            r.rev = e
        }
    }

    private fun Array<Edge?>.backward(sink: Int, block: (Edge) -> Unit) {
        var current = sink
        while (true) {
            val parent = this[current] ?: return
            block(parent)
            current = parent.source
        }
    }

    private fun List<Edge>.binarySearch(destination: Int) = binarySearch { it.destination - destination }
}
