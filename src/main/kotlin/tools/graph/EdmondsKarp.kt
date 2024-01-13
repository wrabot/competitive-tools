package tools.graph

data class EdmondsKarp(private val size: Int, val edges: List<Edge>) {
    class Edge(val source: Int, val destination: Int, val value: Double)

    private val neighbors = edges.indices.groupBy { edges[it].source }
    val flows = Array(size) { DoubleArray(size) }

    fun clear() = flows.forEach { it.fill(0.0) }

    fun maxFlow(source: Int, sink: Int): Double {
        var maxFlow = 0.0
        val todo = mutableListOf<Int>()
        val predecessor = arrayOfNulls<Int>(size)
        while (true) {
            todo.add(source)
            while (predecessor[sink] == null) {
                val current = todo.removeFirstOrNull() ?: return maxFlow
                neighbors[current]?.forEach {
                    val edge = edges[it]
                    val s = edge.source
                    val d = edge.destination
                    if (d != source && predecessor[d] == null && flows[s][d] < edge.value) {
                        predecessor[d] = it
                        todo.add(d)
                    }
                }
            }
            val predecessors = generateSequence(predecessor[sink]) { predecessor[edges[it].source] }.map { edges[it] }
            val df = predecessors.minOf { it.value - flows[it.source][it.destination] }
            predecessors.forEach {
                flows[it.source][it.destination] += df
                flows[it.destination][it.source] -= df
            }
            maxFlow += df
            todo.clear()
            predecessor.indices.forEach { predecessor[it] = null }
        }
    }
}
